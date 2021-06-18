package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPilot;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultMasterController implements MasterController{

    private final RacingPlanReader racingPlanFileReader = new RacingPlanReader();
    private DefaultGameEngine gameEngine;
    private BotController botController;
    private final List<DefaultPilot> pilots = new ArrayList<>();


    public DefaultMasterController () {
    }

    public void gameSettings (String path) throws IOException {
        racingPlanFileReader.setRacingPlan(path);
        DefaultRacingPlan racingPlan = racingPlanFileReader.getRacingPlan();
        this.gameEngine = new DefaultGameEngine(racingPlan);
        this.botController = new BotController(racingPlan);
    }

    public RacingPlanReader getRacingPlanFileReader() {
        return this.racingPlanFileReader;
    }

    public void configurePlayer(String name, PilotType pilotType){
        DefaultRacingVehicle rv = new DefaultRacingVehicle();
        racingPlanFileReader.getRacingPlan().addVehicleToGrid(rv, rv.getId());
        DefaultPilot pilot = new DefaultPilot(name, rv, pilotType);
        this.pilots.add(pilot);
    }

    public void setRacingVehicleMovemenet () {
        pilots.stream()
                .filter(b -> b.getType().equals(PilotType.BOT))
                .forEach(b -> gameEngine.moveRacingVehicle(b.getRacingVehicle(), botController.botNextPosition(b.getRacingVehicle())));
    }

    public List<DefaultPilot> getPilots() {
        return this.pilots;
    }

    public DefaultGameEngine getGameEngine() {
        return this.gameEngine;
    }

    public BotController getBotController() {
        return this.botController;
    }


}
