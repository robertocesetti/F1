package it.unicam.cs.pa2021.f1;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementazioni di default di un veicolo da corsa.
 */
public class DefaultRacingVehicle implements RacingVehicle<DefaultPosition, DefaultAcceleration>{

    private final int id;
    private DefaultPosition position;
    private List<DefaultPosition> trajectory;
    private DefaultAcceleration acceleration;

    public DefaultRacingVehicle(int id, DefaultPosition position) {
        this.id = id;
        this.setPosition(position);
        this.acceleration = new DefaultAcceleration(0,0);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public DefaultPosition getAcceleration() {
        return null;
    }

    @Override
    public DefaultPosition getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(DefaultPosition position) {
        if(position == null) throw new IllegalArgumentException("La posizione passata non e' valida");
        this.position = position;
        this.setTrajectory(position);
    }

    @Override
    public List<DefaultPosition> getTrajectory() {
        return this.trajectory;
    }

    @Override
    public void setTrajectory(DefaultPosition position) {
        this.trajectory.add(position);
    }

    @Override
    public boolean isOut() {
        return getPosition().getStatus().equals(StatusPosition.OUT);
    }

    @Override
    public List<DefaultPosition> nearPositions() {
       return null;
    }
}
