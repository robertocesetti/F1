package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.BotController;
import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Implementazione per una vista su console.
 */
public class ConsoleView implements View {

    DefaultMasterController masterController = new DefaultMasterController();
    String path = "D:\\Workspace Eclipse\\F1\\app\\src\\main\\resources\\Immagine.png";

    @Override
    public void open() {
        try {
            masterController.gameSettings(path);
            masterController.configurePlayer("dam", PilotType.BOT);
            masterController.configurePlayer("rob", PilotType.BOT);
            printRacingPlanConsole();
            statusRace();
        } catch (IOException e) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Errore inserimento file, inserisci il path qui: ");
            this.path = scanner.nextLine();
            if (!this.path.equals("exit"))
                this.open();
            this.close();
        }
    }

    @Override
    public void close() {
        System.out.println("Bye!!!! <3");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Stampa sulla console il piano di gara.
     */
    private void printRacingPlanConsole() throws IOException {
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        int y = racingPlan.getHeight() - 1;
        int x = 0;
        while (y >= 0) {
            while (x < racingPlan.getWidth()) {
                Optional<DefaultPosition> position = racingPlan.getPosition(x, y);
                position.ifPresent(defaultPosition -> this.drawRacingPlanPositions(defaultPosition, racingPlan));
                x++;
            }
            System.out.print("\n");
            x = 0;
            y--;
        }
    }

    /**
     * Disegna le posizioni del piano di gara che verranno visualizzate sulla console.
     *
     * @param position la posizione che verra' disegnata.
     */
    private void drawRacingPlanPositions(DefaultPosition position, DefaultRacingPlan racingPlan) {
        Optional<DefaultRacingVehicle> racingVehicle = racingPlan.isBusy(position);
        if (racingVehicle.isPresent()) {
            System.out.print(racingVehicle.get().getId() + "  ");
            return;
        }
        switch (position.getStatus()) {
            case IN:
                System.out.print("-  ");
                break;
            case GRID:
                System.out.print("G  ");
                break;
            default:
                System.out.print("O  ");
        }
    }

    //TODO SPOSTARE!!!!!!!!!!!!

    public void statusRace() throws IOException {
        BotController botController = masterController.getBotController();
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        while (!isFinish(racingPlan)) {
            System.out.println("\n");
            masterController.setRacingVehicleMovemenet();
            printRacingPlanConsole();
        }
    }

    private boolean isFinish(DefaultRacingPlan racingPlan) {
        return racingPlan.getAllVehicles().stream().anyMatch(r -> r.getPosition().getY() + 1 >= racingPlan.getHeight());
    }


}
