package it.unicam.cs.pa2021.f1.model;

import java.util.Objects;

/**
 * Implementazione di default di una posizione del piano di gara.
 */
public class DefaultPosition implements Position<StatusPosition, DefaultPosition> {

    private final int x;
    private final int y;
    private StatusPosition statusPosition;

    /**
     * Costruttore di una  posizione.
     *
     * @param y la coordinata sull'asse delle y.
     * @param x la coordinata sull'asse delle x.
     */
    public DefaultPosition( int y, int x ) {
        this.x = x;
        this.y = y;
    }

    /**
     * Restituisce la coordinata sull'asse delle x.
     *
     * @return la coordinata sull'asse delle x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Restituisce la coordinata sull'asse delle y.
     *
     * @return la coordinata sull'asse delle y.
     */
    public int getY() {
        return this.y;
    }

    @Override
    public StatusPosition getStatus() {
        return this.statusPosition;
    }

    @Override
    public DefaultPosition setStatus(StatusPosition status) {
        this.statusPosition = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultPosition position = (DefaultPosition) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "DefaultPosition{" +
                "x=" + x +
                ", y=" + y +
                ", statusPosition=" + statusPosition +
                '}';
    }
}
