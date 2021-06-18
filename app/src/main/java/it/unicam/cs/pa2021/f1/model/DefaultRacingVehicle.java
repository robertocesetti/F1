package it.unicam.cs.pa2021.f1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazioni di default di un veicolo da corsa.
 */
public class DefaultRacingVehicle implements RacingVehicle<DefaultPosition, DefaultAcceleration> {

    private final int id;
    private static int idVehicle = 0;
    private DefaultPosition position;
    private List<DefaultPosition> trajectory;
    private DefaultAcceleration acceleration;

    /**
     * Costruttore di un veicolo da corsa.
     */
    public DefaultRacingVehicle() {
        this.id = nextId();
        this.acceleration = new DefaultAcceleration(0, 0);
        this.trajectory = new ArrayList<>();
    }

    @Override
    public int getId() {
        return this.id;
    }

    private int nextId () {
        idVehicle++;
        return idVehicle;
    }

    @Override
    public DefaultAcceleration getAcceleration() {
        return this.acceleration;
    }

    @Override
    public void setAcceleration(DefaultAcceleration acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Aggiorna l'accelerazione del veicolo
     *
     * @param x coordinata x del vettore accelerazione.
     * @param y coordinata y del vettore accelerazione.
     */
    public void updateAcceleration(int x, int y) {
        this.acceleration.setX(x);
        this.acceleration.setY(y);
    }

    @Override
    public DefaultPosition getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(DefaultPosition position) throws NullPointerException {
        if (position == null) throw new NullPointerException("La posizione che si vuole assegnare non e' valida");
        if (this.position != null) {
            this.updateTrajectory(this.position);
            this.updateAcceleration(position.getX() - this.position.getX(), position.getY() - this.position.getY());
        }
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
    public void updateTrajectory(DefaultPosition position) throws NullPointerException {
        if (position == null) throw new NullPointerException("La posizione passata e' nulla");
        this.trajectory.add(position);    }

    @Override
    public boolean isOut() {
        return this.position.getStatus().equals(StatusPosition.OUT);
    }

}
