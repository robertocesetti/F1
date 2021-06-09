package it.unicam.cs.pa2021.f1.model;

/**
 * Implementazione di default di una posizione del piano di gara.
 */
public class DefaultPosition implements Position {

    private final int x;
    private final int y;
    private final StatusPosition statusPosition;

    public DefaultPosition(int x, int y, StatusPosition statusPosition) {
        this.x = x;
        this.y = y;
        this.statusPosition = statusPosition;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    @Override
    public StatusPosition getStatus() {
        return this.statusPosition;
    }

}
