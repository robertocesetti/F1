package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.*;

import java.io.IOException;

public class DefaultMasterController implements MasterController {

    private final RacingPlanReader racingPlanFileReader = new RacingPlanReader();
    private DefaultGameEngine gameEngine;
    private BotController botController;
    private DefaultReferee referee;

    public DefaultMasterController() {
    }

    @Override
    public void gameSettings(String path) throws IOException {
        racingPlanFileReader.setRacingPlan(path);
        DefaultRacingPlan racingPlan = racingPlanFileReader.getRacingPlan();
        this.gameEngine = new DefaultGameEngine(racingPlan);
        this.botController = new BotController(racingPlan);
        this.referee = new DefaultReferee();
    }

    public void configurePlayer(String name, PilotType pilotType) {
        DefaultRacingVehicle rv = new DefaultRacingVehicle();
        racingPlanFileReader.getRacingPlan().addVehicleToGrid(rv, rv.getId());
        referee.createPilot(name, rv, pilotType);
    }

    public void setRacingVehicleMovemenet(DefaultPilot pilot, DefaultPosition position) {
        DefaultRacingVehicle vehicle = pilot.getRacingVehicle();
        if(position == null) position = botController.botNextPosition(vehicle);
        gameEngine.moveRacingVehicle(vehicle, position);
    }

    @Override
    public RacingPlanReader getRacingPlanFileReader() {
        return this.racingPlanFileReader;
    }


    @Override
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

    public DefaultReferee getReferee() {
        return referee;
    }
}
