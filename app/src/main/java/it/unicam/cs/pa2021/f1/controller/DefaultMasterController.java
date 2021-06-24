package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

/**
 * Implementazione di default del controller principale.
 */
public class DefaultMasterController implements MasterController<RacingPlanReader, DefaultGameEngine, BotController, DefaultReferee> {

    private RacingPlanReader racingPlanFileReader;
    private DefaultGameEngine gameEngine;
    private BotController botController;
    private DefaultReferee referee;
    private Image carPlayer;
    private Image carBot;

    public DefaultMasterController() { }

    @Override
    public void newGame(String path) throws IOException {
        DefaultRacingVehicle.resetId();
        racingPlanFileReader = new RacingPlanReader();
        this.referee = new DefaultReferee();
        racingPlanFileReader.setRacingPlan(path);
        DefaultRacingPlan racingPlan = racingPlanFileReader.getRacingPlan();
        this.gameEngine = new DefaultGameEngine(racingPlan);
        this.botController = new BotController(racingPlan);
        carPlayer = getImage("/car.png");
        carBot = getImage("/carBot.png");
    }

    @Override
    public int configurePlayer(String name, PilotType pilotType) {
        DefaultRacingVehicle rv = new DefaultRacingVehicle();
        rv.setSkin(configureSkin(pilotType));
        racingPlanFileReader.getRacingPlan().addVehicleToGrid(rv, rv.getId());
        referee.createPilot(name, rv, pilotType);
        return rv.getId();
    }

    private Image configureSkin(PilotType pilotType) {
        return pilotType.equals(PilotType.PLAYER) ? carPlayer : carBot;

    }

    @Override
    public boolean isFinish(DefaultRacingPlan racingPlan) {
        referee.setFinish(racingPlan.getAllVehicles().stream().anyMatch(r -> r.getPosition().getY() <= 0));
        return referee.getIsFinish();
    }

    /**
     * Effettua lo spostamento del veicolo del pilota.
     *
     * @param pilot    il pilota che possiede del veicolo da muovere.
     * @param position la posizione nella quale verra' messo il veicolo.
     * @throws IllegalArgumentException se la posizione passata non e' valida.
     * @throws NullPointerException     se la posizione o il veicolo e' nullo.
     */
    public void setRacingVehicleMovement(DefaultPilot pilot, DefaultPosition position) throws IllegalArgumentException, NullPointerException {
        DefaultRacingVehicle vehicle = pilot.getRacingVehicle();
        if (position == null) position = botController.botNextPosition(vehicle);
        gameEngine.moveRacingVehicle(vehicle, position);
        getReferee().nextTurn();
    }

    /**
     * Restituisce il lettore del piano di gara.
     *
     * @return il lettore del piano di gara.
     */
    public RacingPlanReader getRacingPlanFileReader() {
        return this.racingPlanFileReader;
    }

    /**
     * Restituisce il motore di gioco.
     *
     * @return il motore di gioco.
     */
    public DefaultGameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * Restituisce il bot controller.
     *
     * @return il bot controller.
     */
    public BotController getBotController() {
        return this.botController;
    }

    /**
     * Restituisce l'arbitro.
     *
     * @return l'arbitro.
     */
    public DefaultReferee getReferee() {
        return referee;
    }

    /**
     * Restituisce un'immagine da un path.
     *
     * @param path il percorso dove prendere l'immagine.
     * @return l'immagine.
     */
    private Image getImage(String path) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
    }

}
