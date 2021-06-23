package it.unicam.cs.pa2021.f1.view;

import it.unicam.cs.pa2021.f1.controller.DefaultMasterController;
import it.unicam.cs.pa2021.f1.model.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Implementazione per una vista su Console.
 */
public class ConsoleView implements View {

    private final DefaultMasterController masterController = new DefaultMasterController();
    private String path = Objects.requireNonNull(getClass().getResourceAsStream("/Immagine.png")).toString();
    private Scanner scanner;

    @Override
    public void open() {
        try {
            scanner = new Scanner(System.in);
            masterController.newGame(path);
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
        System.out.println("Bye!!");
    }

    /**
     * Stampa sulla console il piano di gara.
     */
    private void printRacingPlanConsole() throws IOException {
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        int y = 0;
        int x = 0;
        while (y < racingPlan.getHeight()) {
            while (x < racingPlan.getWidth()) {
                Optional<DefaultPosition> position = racingPlan.getPosition(x, y);
                position.ifPresent(defaultPosition -> this.drawRacingPlanPositions(defaultPosition, racingPlan));
                x++;
            }
            System.out.print("\n");
            x = 0;
            y++;
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
                System.out.print("(" + position.getX() + ", " + position.getY() + ")");
                break;
            case GRID:
                System.out.print("(" + position.getX() + ", " + position.getY() + ")");
                break;
            case FINISH:
                System.out.print("(" + position.getX() + ", " + position.getY() + ")");
                break;
            default:
                System.out.print("O  ");
        }
    }

    /**
     * Stampa lo stato della pista ad ogni spostamento dei veicoli.
     *
     * @throws IOException se il file non viene letto correttamente.
     */
    private void statusRace() throws IOException {
        DefaultRacingPlan racingPlan = masterController.getRacingPlanFileReader().getRacingPlan();
        while (!masterController.isFinish(racingPlan)) {
            try {
                System.out.println("\n");
                DefaultPilot pilot = masterController.getReferee().pilotTurn();
                masterController.setRacingVehicleMovemenet(pilot, positionByPilot(pilot));
                printRacingPlanConsole();
            } catch (IllegalArgumentException e) {
                System.out.println("Eccezione: " + e.getMessage());
            }
        }
        System.out.println("Fine della gara!!");
        System.out.println("Il vincitore e': ");
    }

    /**
     * Sposta il veicolo del pilota.
     *
     * @param pilot il pilota.
     * @return la posizione in cui e' verra' spostato.
     */
    private DefaultPosition positionByPilot(DefaultPilot pilot) {
        if (pilot.getType().equals(PilotType.BOT)) {
            return null;
        } else {
            int x;
            int y;
            masterController.getGameEngine().allNearPosition(pilot.getRacingVehicle()).forEach(System.out::println);
            System.out.println("Inserisci una posizione tra quelle mostrate.");
            System.out.println("x: ");
            x = scanner.nextInt();
            System.out.println("y: ");
            y = scanner.nextInt();
            Optional<DefaultPosition> position = masterController.getGameEngine().getRacingPlan().getPosition(x, y);
            return position.orElse(null);
        }
    }

    /**
     * Crea i bot che parteciperanno alla gara.
     */
    private void allBot() {
        System.out.println("Inserisci il numero di giocatori bot ");
        int numberOfBot = scanner.nextInt();
        for (int i = 1; i <= numberOfBot; i++) {
            masterController.configurePlayer("bot" + i, PilotType.BOT);
        }
    }

    /**
     * Crea un player in base a cio' che e' stato inserito dall'utente sulla console.
     *
     * @param name il nome del player.
     * @return l'id del veicolo del player.
     */
    private int playerSettings(String name) {
        return masterController.configurePlayer(name, PilotType.PLAYER);
    }

    /**
     * Permette di creare piu' players.
     */
    private void allPlayers() {
        String risposta;
        do {
            System.out.println("Inserisci il nome del giocatore ");
            String nameOfPlayer = scanner.nextLine();
            System.out.println("L'id del tuo veicolo e': " + playerSettings(nameOfPlayer));
            System.out.println("Vuoi aggiungere un altro giocatore ? (s per si) ");
            risposta = scanner.nextLine();
        } while (risposta.equals("s"));
    }

}
