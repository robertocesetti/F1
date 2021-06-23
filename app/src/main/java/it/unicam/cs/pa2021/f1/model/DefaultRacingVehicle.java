package it.unicam.cs.pa2021.f1.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementazioni di default di un veicolo da corsa.
 */
public class DefaultRacingVehicle implements RacingVehicle<DefaultPosition, DefaultAcceleration, Image> {

    private static int idVehicle = 0;
    private final int id;
    private DefaultPosition position;
    private List<DefaultPosition> trajectory;
    private DefaultAcceleration acceleration;
    private Image skin;

    /**
     * Costruttore di un veicolo da corsa.
     */
    public DefaultRacingVehicle() {
        this.id = nextId();
        this.acceleration = new DefaultAcceleration(0, 0);
        this.trajectory = new ArrayList<>();
    }

    public static void resetId() {
        idVehicle = 0;
    }

    @Override
    public int getId() {
        return this.id;
    }

    private int nextId() {
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
    public void setPosition(DefaultPosition newPosition) throws NullPointerException {
        if (newPosition == null) throw new NullPointerException("La posizione che si vuole assegnare non e' valida");
        if (this.position != null) {
            this.updateTrajectory(this.position);
            this.updateAcceleration(newPosition.getX() - this.position.getX(), this.position.getY() - newPosition.getY());
        }
        this.position = newPosition;
    }

    @Override
    public Image getSkin() {
        return this.skin;
    }

    /**
     * Imposta la skin del veicolo.
     *
     * @param skin del veicolo.
     */
    public void setSkin(Image skin) {
        this.skin = skin;
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
        this.trajectory.add(position);
    }

    @Override
    public boolean isOut() {
        return this.position.getStatus().equals(StatusPosition.OUT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultRacingVehicle that = (DefaultRacingVehicle) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
