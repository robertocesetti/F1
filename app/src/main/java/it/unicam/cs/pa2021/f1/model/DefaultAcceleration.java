package it.unicam.cs.pa2021.f1.model;

import java.util.Objects;

/**
 * Implementazione di default dell'accelerazione di un veicolo.
 */
public class DefaultAcceleration implements Acceleration<Integer> {

    private int x;
    private int y;

    /**
     * Costruttore del vettore accelerazione.
     *
     * @param x coordinata x del vettore.
     * @param y coordinata y del vettore.
     */
    public DefaultAcceleration(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer getX() {
        return this.x;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public Integer getY() {
        return this.y;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultAcceleration that = (DefaultAcceleration) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "DefaultAcceleration{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

