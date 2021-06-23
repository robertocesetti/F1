package it.unicam.cs.pa2021.f1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test sulla correttezza dello spostamento effettuato dai veicoli.
 */
class DefaultRacingVehicleTest {

    DefaultRacingVehicle racingVehicle = new DefaultRacingVehicle();

    @Test
    public void shouldChangePosition() {

        DefaultPosition position = new DefaultPosition(0,0);
        racingVehicle.setPosition(position);
        assertEquals(new DefaultAcceleration(0,0), racingVehicle.getAcceleration());

        DefaultPosition newPosition = new DefaultPosition(3,0);
        racingVehicle.setPosition(newPosition);
        assertEquals(new DefaultAcceleration(0, -3), racingVehicle.getAcceleration());

        DefaultPosition newPosition1 = new DefaultPosition(3,3);
        racingVehicle.setPosition(newPosition1);
        assertEquals(new DefaultAcceleration(3, 0), racingVehicle.getAcceleration());

        DefaultPosition newPosition2 = new DefaultPosition(5,5);
        racingVehicle.setPosition(newPosition2);
        assertEquals(new DefaultAcceleration(2, -2), racingVehicle.getAcceleration());

    }

}