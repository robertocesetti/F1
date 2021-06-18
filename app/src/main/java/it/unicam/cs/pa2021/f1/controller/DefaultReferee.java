package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPilot;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementazione di un arbitro.
 */
public class DefaultReferee implements Referee<DefaultPilot> {

    private final Map<Integer,DefaultPilot> pilots = new HashMap<>();
    private int turn = 0;
    private int numberPilots = 0;



    public void createPilot(String name, DefaultRacingVehicle rv, PilotType pilotType) {
        DefaultPilot pilot = new DefaultPilot(name, rv, pilotType);
        this.pilots.put(rv.getId(), pilot);
    }

    @Override
    public Map<Integer,DefaultPilot> getPilots() {
        return this.pilots;
    }


    public DefaultPilot pilotTurn() {
        this.numberPilots = pilots.size();
        return pilots.get(turn%numberPilots);
    }

    public int getTurn() {
        return turn;
    }

    int nextTurn() {
        turn++;
        return turn;
    }

}
