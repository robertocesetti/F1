package it.unicam.cs.pa2021.f1.model;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementazione di default di una posizione del piano di gara.
 */
public class DefaultPosition implements Position<StatusPosition, DefaultPosition> {

    private final int x;
    private final int y;
    private StatusPosition statusPosition;

    /**
     * Costruttore di una  poszione.
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

/*
    public Set<DefaultPosition> getNearPositions(DefaultAcceleration acceleration) {
        int traslateX = this.x + acceleration.getX();
        int traslateY = this.y + acceleration.getY();
        return Stream.of(this.center(traslateX, traslateY),
                this.above(traslateX, traslateY),
                this.aboveLeft(traslateX, traslateY),
                this.aboveRight(traslateX, traslateY),
                this.below(traslateX, traslateY),
                this.belowLeft(traslateX, traslateY),
                this.belowRight(traslateX, traslateY),
                this.left(traslateX, traslateY),
                this.right(traslateX, traslateY)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
    }

    public Optional<DefaultPosition> center(int x, int y) { return near (x,y,0,0); }

    public Optional<DefaultPosition> above(int x, int y) { return near (x,y,0, +1); }

    public Optional<DefaultPosition> aboveLeft(int x, int y) { return near (x,y,-1, +1); }

    public Optional<DefaultPosition> aboveRight(int x, int y) { return near (x,y, +1, +1); }

    public Optional<DefaultPosition> right(int x, int y) { return near (x,y, +1, 0); }

    public Optional<DefaultPosition> left(int x, int y) { return near (x,y, -1, 0); }

    public Optional<DefaultPosition> below(int x, int y) { return near (x,y, 0, -1); }

    public Optional<DefaultPosition> belowLeft(int x, int y) { return near (x,y, -1 , -1); }

    public Optional<DefaultPosition> belowRight(int x, int y) { return near (x,y, +1, -1); }

     TODO Controllo

    private Optional<DefaultPosition> near (int x, int y, int dirX, int dirY){
        int newX = x+dirX;
        int newY = y+dirY;
        return null;
        //Optional.of(new DefaultPosition(newX,newY));
    }
 */
}
