package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test sulla correttezza dell'algoritmo che scopre le posizioni vicine ad una posizione data
 * a seconda delle informazioni fornite dal veicolo.
 */
class DefaultGameEngineTest {

    DefaultGameEngine gameEngine;
    DefaultPosition position1;
    DefaultPosition position2;
    DefaultPosition position3;
    DefaultPosition position4;
    DefaultPosition position5;
    DefaultPosition position6;
    DefaultPosition position7;
    DefaultPosition position8;
    DefaultPosition position9;
    List<DefaultPosition> positions = new ArrayList<>();
    DefaultRacingPlan racingPlan;
    DefaultRacingVehicle racingVehicle;
    List<DefaultPosition> correctPositions = new ArrayList<>();
    List<DefaultPosition> filteredPositions = new ArrayList<>();
    List<DefaultPosition> wrongPositions = new ArrayList<>();

    @BeforeEach
    void inizialize() {

        position1 = new DefaultPosition(0, 0);
        position2 = new DefaultPosition(0, 1);
        position3 = new DefaultPosition(0, 2);
        position4 = new DefaultPosition(1, 0);
        position5 = new DefaultPosition(1, 1);
        position6 = new DefaultPosition(1, 2);
        position7 = new DefaultPosition(2, 0);
        position8 = new DefaultPosition(2, 1);
        position9 = new DefaultPosition(2, 2);
        position1.setStatus(StatusPosition.FINISH);
        position2.setStatus(StatusPosition.FINISH);
        position3.setStatus(StatusPosition.FINISH);
        position4.setStatus(StatusPosition.IN);
        position5.setStatus(StatusPosition.OUT);
        position6.setStatus(StatusPosition.IN);
        position7.setStatus(StatusPosition.GRID);
        position8.setStatus(StatusPosition.GRID);
        position9.setStatus(StatusPosition.GRID);
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
        positions.add(position4);
        positions.add(position5);
        positions.add(position6);
        positions.add(position7);
        positions.add(position8);
        positions.add(position9);
        racingPlan = new DefaultRacingPlan(3, 3, positions);
        racingVehicle = new DefaultRacingVehicle();
        racingPlan.addVehicleToGrid(racingVehicle, 1);
        gameEngine = new DefaultGameEngine(racingPlan);
    }

    @Test
    void allNearPosition() {
        filteredPositions = gameEngine.allNearPosition(racingVehicle);
        correctPositions.add(position4);
        correctPositions.add(position6);
        correctPositions.add(position5);
        wrongPositions.add(position1);
        wrongPositions.add(position2);
        wrongPositions.add(position3);
        assertTrue(correctPositions.containsAll(filteredPositions));
        assertFalse(wrongPositions.containsAll(filteredPositions));
    }
}