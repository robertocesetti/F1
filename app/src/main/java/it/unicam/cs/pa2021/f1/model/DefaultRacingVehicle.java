package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Set;

/**
 * Implementazioni di default di un veicolo da corsa.
 */
public class DefaultRacingVehicle implements RacingVehicle<Integer, DefaultPosition, DefaultAcceleration> {

    private final int id;
    private DefaultPosition position;
    private List<DefaultPosition> trajectory;
    private DefaultAcceleration acceleration;

    public DefaultRacingVehicle(int id, DefaultPosition position, List<DefaultPosition> trajectory) {
        this.id = id;
        this.position = position;
        this.trajectory = trajectory;
        this.acceleration = new DefaultAcceleration(0,0);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public DefaultAcceleration getAcceleration() { return this.acceleration; }

    @Override
    public void setAcceleration(DefaultAcceleration acceleration) { this.acceleration = acceleration; }

    @Override
    public DefaultPosition getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(DefaultPosition position) {
        if(position == null) throw new IllegalArgumentException("La posizione che si vuole raggiungere non e' valida");
        this.position = position;
        this.updateTrajectory(position);
    }

    @Override
    public List<DefaultPosition> getTrajectory() {
        return this.trajectory;
    }

    @Override
    public void setTrajectory(List<DefaultPosition> trajectory) { this.trajectory = trajectory; }

    @Override
    public void updateTrajectory(DefaultPosition position) {
        this.trajectory.add(position);
    }

    @Override
    public boolean isOut() {
        return getPosition().getStatus().equals(StatusPosition.OUT);
    }

    @Override
    public Set<DefaultPosition> nearPositions() {
       return null; //TODO this.position.getNearPositions(this.acceleration);
    }

}
