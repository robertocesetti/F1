package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementazione di default del piano gara.
 */
public class DefaultRacingPlan implements RacingPlan<DefaultRacingVehicle, DefaultPosition> {

    private List<DefaultRacingVehicle> vehicles;
    private List<DefaultPosition> grid;
    private List<DefaultPosition> allPositions;

    public DefaultRacingPlan(List<DefaultRacingVehicle> vehicles, List<DefaultPosition> grid, List<DefaultPosition> allPositions) {
        this.vehicles = vehicles;
        this.grid = grid;
        this.allPositions = allPositions;
    }

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
    public List<DefaultPosition> getTrackLayout() {
        return getAllPositions().parallelStream()
                .filter(p -> p.getStatus().equals(StatusPosition.IN))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addVehicleToGrid(DefaultRacingVehicle racingVehicle, DefaultPosition position) {
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
    public void toPosition(DefaultRacingVehicle racingVehicle, DefaultPosition nextPosition) {
        if (racingVehicle == null || nextPosition == null) throw new NullPointerException("");
        Set<DefaultPosition> nearPositions = racingVehicle.nearPositions();
        if (!nearPositions.contains(nextPosition)) throw new IllegalArgumentException();
        racingVehicle.setPosition(nextPosition);
    }

}