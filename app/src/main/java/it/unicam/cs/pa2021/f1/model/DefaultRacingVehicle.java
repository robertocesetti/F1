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

    /**
     * Costruttore di un veicolo da corsa.
     *
     * @param id l'identificativo del veicolo.
     */
    public DefaultRacingVehicle(int id) {
        this.id = id;
        this.acceleration = new DefaultAcceleration(0, 0);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public DefaultAcceleration getAcceleration() {
        return this.acceleration;
    }

    @Override
    public void setAcceleration(DefaultAcceleration acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public DefaultPosition getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(DefaultPosition position) throws NullPointerException {
        if (position == null) throw new NullPointerException("La posizione che si vuole raggiungere non e' valida");
        if (this.position != null) this.updateTrajectory(this.position);
        this.position = position;
    }

    @Override
    public List<DefaultPosition> getTrajectory() {
        return this.trajectory;
    }

    @Override
    public void setTrajectory(List<DefaultPosition> trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public void updateTrajectory(DefaultPosition position) {
        if (position != null) this.trajectory.add(position);
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
