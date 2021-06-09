package it.unicam.cs.pa2021.f1;

public class DefaultAcceleration implements Acceleration<Number>{

    private int x;
    private int y;

    public DefaultAcceleration(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Number getX() {
        return this.x;
    }

    @Override
    public Number getY() {
        return this.y;
    }
}
