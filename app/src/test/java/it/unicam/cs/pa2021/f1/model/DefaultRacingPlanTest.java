package it.unicam.cs.pa2021.f1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test sulla correttezza delle informazioni relative alle singole posizioni del piano di gara.
 */
class DefaultRacingPlanTest {

    DefaultRacingPlan racingPlan;
    DefaultPosition position1;
    DefaultPosition position2;
    DefaultPosition position3;
    DefaultPosition position4;
    DefaultPosition position5;
    DefaultPosition position6;
    List<DefaultPosition> positions = new LinkedList<>();
    List<DefaultRacingVehicle> allVehicles = new LinkedList<>();
    List<DefaultPosition> grid = new LinkedList<>();
    List<DefaultPosition> finishLine = new LinkedList<>();
    DefaultRacingVehicle racingVehicle = new DefaultRacingVehicle();
    DefaultRacingVehicle racingVehicle1 = new DefaultRacingVehicle();

    @BeforeEach
    void inizialize() {
        position1 = new DefaultPosition(0,0);
        position2 = new DefaultPosition(0,1);
        position3 = new DefaultPosition(1,0);
        position4 = new DefaultPosition(1,1);
        position5 = new DefaultPosition(2,0);
        position6 = new DefaultPosition(2,1);
        position1.setStatus(StatusPosition.GRID);
        position2.setStatus(StatusPosition.GRID);
        position3.setStatus(StatusPosition.IN);
        position4.setStatus(StatusPosition.OUT);
        position5.setStatus(StatusPosition.FINISH);
        position6.setStatus(StatusPosition.FINISH);
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
        positions.add(position4);
        positions.add(position5);
        positions.add(position6);
        allVehicles.add(racingVehicle);
        allVehicles.add(racingVehicle1);
        finishLine.add(position5);
        finishLine.add(position6);
        grid.add(position1);
        grid.add(position2);
        racingPlan = new DefaultRacingPlan(3,2, positions);
    }

    @Test
    void getPosition() {

        assertEquals(racingPlan.getPosition(0,0).get(), position1);
        assertNotEquals(racingPlan.getPosition(0,0).get(), position2);

    }

    @Test
    void isBusy() {
        racingVehicle1.setPosition(position3);
        racingVehicle.setPosition(position1);
        assertEquals(racingVehicle1.getPosition(), racingPlan.getPosition(position3.getX(),position3.getY()).get());
    }

    @Test
    void getGrid() {
        assertEquals(grid, racingPlan.getGrid());
    }

    @Test
    void getFinishLine() {
        assertEquals(finishLine, racingPlan.getFinishLine());
    }

}