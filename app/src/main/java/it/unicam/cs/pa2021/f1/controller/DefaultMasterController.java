package it.unicam.cs.pa2021.f1.controller;

import java.io.IOException;

public class DefaultMasterController implements MasterController{

    RacingPlanReader racingPlanFileReader = new RacingPlanReader();
    DefaultGameEngine gameEngine;

    public DefaultMasterController () {
    }

    public void setGameEngine() throws IOException {
        this.gameEngine = new DefaultGameEngine(racingPlanFileReader.getRacingPlan());
    }
}
