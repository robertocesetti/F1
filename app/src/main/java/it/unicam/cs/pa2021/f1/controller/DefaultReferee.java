package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPilot;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione di default di un arbitro.
 */
public class DefaultReferee implements Referee<DefaultPilot> {

    private final List<DefaultPilot> pilots = new ArrayList<>();
    private int turn = 0;
    private int numberPilots = 0;

    /**
     * Crea il pilota.
     *
     * @param name nome del pilota.
     * @param racingVehicle veicolo.
     * @param pilotType tipo di pilota.
     */
    public void createPilot(String name, DefaultRacingVehicle racingVehicle, PilotType pilotType) {
        DefaultPilot pilot = new DefaultPilot(name, racingVehicle, pilotType);
        this.pilots.add(pilot);
    }

    @Override
    public List<DefaultPilot> getPilots() {
        return this.pilots;
    }

    @Override
    public DefaultPilot pilotTurn() {
        this.numberPilots = pilots.size();
        return pilots.get(turn % numberPilots);
    }

    /**
     * Restituisce il numero del turno della partita.
     *
     * @return Il numero del turno della partita.
     */
    public int getNumberOfTurn() {
        return this.turn / this.numberPilots;
    }

    /**
     * Passa al turno successivo.
     */
    void nextTurn() {
        this.turn++;
    }

}
