package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPilot;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;
import it.unicam.cs.pa2021.f1.model.PilotType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test sulla correttezza dell'assegnazione del turno da parte dell'arbitro ai giocatori.
 */
class DefaultRefereeTest {

    DefaultReferee referee;
    int turn;
    int numberOfPilots;
    DefaultRacingVehicle racingVehicle = new DefaultRacingVehicle();
    List<DefaultPilot> pilots = new LinkedList<>();

    @BeforeEach
    void inizialize() {
        referee = new DefaultReferee();
        referee.createPilot("roberto", racingVehicle, PilotType.PLAYER);
        numberOfPilots = pilots.size();
        turn = 0;
    }

    @Test
    void pilotTurn() {
        assertEquals(referee.getPilots().get(0), referee.pilotTurn());
    }
}