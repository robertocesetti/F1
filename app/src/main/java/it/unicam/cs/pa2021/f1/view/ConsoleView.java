package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.BotController;
import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Implementazione per una vista su console.
 */
public class ConsoleView implements View {

    private final DefaultMasterController masterController = new DefaultMasterController();
    private String path = "D:\\Workspace Eclipse\\F1\\app\\src\\main\\resources\\Immagine.png";
    private Scanner scanner;

    @Override
    public void open() {
        try {
            scanner = new Scanner(System.in);
            masterController.gameSettings(path);
            allPlayers();
            allBot();
            printRacingPlanConsole();
            statusRace();
        } catch (IOException e) {
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

    private void statusRace() throws IOException {
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        while (!masterController.isFinish(racingPlan)) {
            System.out.println("\n");
            DefaultPilot pilot = masterController.getReferee().pilotTurn();
            masterController.setRacingVehicleMovemenet(pilot, positionByPilot(pilot));
            printRacingPlanConsole();
        }
    }

    private DefaultPosition positionByPilot(DefaultPilot pilot) {
        if (pilot.getType().equals(PilotType.BOT)) return null;
        int x;
        int y;
        DefaultPosition position;
        masterController.getGameEngine().getNearPositions(pilot.getRacingVehicle()).forEach(System.out::println);
        System.out.println("Inserisci una posizione tra quelle mostrate.");
        System.out.println("x: ");
        x = scanner.nextInt();
        System.out.println("y: ");
        y = scanner.nextInt();
        return position = new DefaultPosition(y, x);
    }

    private void allBot() {
        System.out.println("Inserisci il numero di giocatori bot ");
        int numberOfBot = scanner.nextInt();
        for (int i = 1; i <= numberOfBot; i++) {
            masterController.configurePlayer("bot" + i, PilotType.BOT);
        }
    }

    private void playerSettings(String name) {
        masterController.configurePlayer(name, PilotType.PLAYER);
    }

    private void allPlayers() {
        String risposta = "n";
        do {
            System.out.println("Inserisci il nome del giocatore ");
            String nameOfPlayer = scanner.nextLine();
            playerSettings(nameOfPlayer);
            System.out.println("Vuoi aggiungerne un altro? (s per si) ");
            risposta = scanner.nextLine();
        } while(risposta.equals("s"));
    }
}
