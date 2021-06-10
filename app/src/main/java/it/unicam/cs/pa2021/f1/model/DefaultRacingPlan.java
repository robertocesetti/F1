package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Set;

/**
 * Implementazione di default del piano gara.
 */
public class DefaultRacingPlan implements RacingPlan {

    private List<RacingVehicle> vehicles;
    private List<Position> grid;
    private List<Position> allPositions;

    public DefaultRacingPlan(List<RacingVehicle> vehicles, List<Position> grid, List<Position> allPositions) {
        this.vehicles = vehicles;
        this.grid = grid;
        this.allPositions = allPositions;
    }

    @Override
    public List<RacingVehicle> getAllVehicle() {
        return this.vehicles;
    }

    @Override
    public List<Position> getGrid() {
        return this.grid;
    }

    @Override
    public List<Position> getAllPositions() {
        return this.allPositions;
    }

    @Override
    public boolean addVehicleToGrid(RacingVehicle racingVehicle, Position position) {
        if (racingVehicle == null || position == null)
            throw new NullPointerException("Il veicolo o la poszione non possono essere nulli");
        if (!grid.contains(position))
            throw new IllegalArgumentException("La posizione inserita non fa parte della griglia");
        try {
            racingVehicle.setPosition(position);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void toPosition(RacingVehicle racingVehicle, Position nextPosition) {
        if (racingVehicle == null || nextPosition == null) throw new NullPointerException("");
        Set<Position> nearPositions = racingVehicle.nearPositions();
        if (!nearPositions.contains(nextPosition)) throw new IllegalArgumentException();
        racingVehicle.setPosition(nextPosition);
    }

}
