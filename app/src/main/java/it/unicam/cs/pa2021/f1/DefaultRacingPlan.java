package it.unicam.cs.pa2021.f1;

import java.util.List;

/**
 * Implementazione di default del piano gara.
 */

public class DefaultRacingPlan implements RacingPlan<DefaultPosition, DefaultRacingVehicle> {

    private List<DefaultRacingVehicle> vehicles;
    private List<DefaultPosition> grid;
    private List<DefaultPosition> allPositions;

    @Override
    public List<DefaultRacingVehicle> getAllVehicle() {
        return this.vehicles;
    }

    @Override
    public List<DefaultPosition> getGrid() {
        return this.grid;
    }

    @Override
    public List<DefaultPosition> getAllPositions() {
        return this.allPositions;
    }

    @Override
    public boolean addVehicle(DefaultRacingVehicle racingVehicle, DefaultPosition position) {
        return false;
    }

    @Override
    public List<Position> nextPositions(DefaultPosition vehiclePosition) {
        return null;
    }


}
