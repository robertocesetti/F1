package it.unicam.cs.pa2021.f1.controller;


import it.unicam.cs.pa2021.f1.model.DefaultPilot;

import java.util.Map;

public interface Referee<P> {

    /**
     * Restituisce i piloti che gareggiano.
     *
     * @return i piloti che gareggiano.
     */
    Map<Integer, DefaultPilot> getPilots();

    /**
     * Restituisce il pilota del turno.
     *
     * @return il pilota del turno.
     */
    P pilotTurn();

}
