package it.unicam.cs.pa2021.f1.model;

import java.util.List;

/**
 * Implementazioni di default di un veicolo da corsa.
 */
public class DefaultRacingVehicle implements RacingVehicle {

    private final int id;
    private Position position;
    private List<Position> trajectory;
    private Acceleration acceleration;

    public DefaultRacingVehicle(int id, Position position) {
        this.id = id;
        this.setPosition(position);
        this.acceleration = new DefaultAcceleration(0,0);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Position getAcceleration() {
        return null;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        if(position == null) throw new IllegalArgumentException("La posizione passata non e' valida");
        this.position = position;
        this.setTrajectory(position);
    }

    @Override
    public List<Position> getTrajectory() {
        return this.trajectory;
    }

    @Override
    public void setTrajectory(Position position) {
        this.trajectory.add(position);
    }

    @Override
    public boolean isOut() {
        return getPosition().getStatus().equals(StatusPosition.OUT);
    }

    @Override
    public List<Position> nearPositions() {
       return null;
    }
}
