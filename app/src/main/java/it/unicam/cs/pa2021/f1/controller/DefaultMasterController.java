package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.*;

import java.io.IOException;

/**
 * Implementazione di default del controller principale.
 */
public class DefaultMasterController implements MasterController<RacingPlanReader, DefaultGameEngine, BotController, DefaultReferee> {

    private final RacingPlanReader racingPlanFileReader = new RacingPlanReader();
    private DefaultGameEngine gameEngine;
    private BotController botController;
    private DefaultReferee referee;

    @Override
    public void gameSettings(String path) throws IOException {
        racingPlanFileReader.setRacingPlan(path);
        DefaultRacingPlan racingPlan = racingPlanFileReader.getRacingPlan();
        this.gameEngine = new DefaultGameEngine(racingPlan);
        this.botController = new BotController(racingPlan);
        this.referee = new DefaultReferee();
    }

    /**
     *  Configura un giocatore.
     *
     * @param name del giocatore.
     * @param pilotType il tipo (BOT o PLAYER).
     * @return l'id del veicolo del pilota del giocatore.
     */
    public int configurePlayer(String name, PilotType pilotType) {
        DefaultRacingVehicle rv = new DefaultRacingVehicle();
        racingPlanFileReader.getRacingPlan().addVehicleToGrid(rv, rv.getId());
        referee.createPilot(name, rv, pilotType);
        return  rv.getId();
    }

    /**
     * Effettua lo spostamento del veicolo del pilota.
     *
     * @param pilot il pilota possessore del veicolo da muovere.
     * @param position la posizione nella quale verra' messo il veicolo.
     * @throws IllegalArgumentException se la posizione passata non e' valida
     * @throws NullPointerException se la posizione o il veicolo e' nullo.
     */
    public void setRacingVehicleMovemenet(DefaultPilot pilot, DefaultPosition position) throws IllegalArgumentException, NullPointerException {
        DefaultRacingVehicle vehicle = pilot.getRacingVehicle();
        if(position == null) position = botController.botNextPosition(vehicle);
        gameEngine.moveRacingVehicle(vehicle, position);
        getReferee().nextTurn();
    }

    @Override
    public RacingPlanReader getRacingPlanFileReader() {
        return this.racingPlanFileReader;
    }

    /**
     * Restituisce true se qualche veicolo ha raggiunto la fine del tracciatofalse altrimenti.
     *
     * @param racingPlan il piano di gara.
     * @return true se qualche veicolo ha raggiunto la fine del tracciato, false altrimenti.
     */
    public boolean isFinish(DefaultRacingPlan racingPlan) {
        return racingPlan.getAllVehicles().stream().anyMatch(r -> r.getPosition().getY() + 1 >= racingPlan.getHeight());
    }

    @Override
    public DefaultGameEngine getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public BotController getBotController() {
        return this.botController;
    }

    @Override
    public DefaultReferee getReferee() {
        return referee;
    }

}
