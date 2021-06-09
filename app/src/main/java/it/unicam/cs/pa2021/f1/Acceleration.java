package it.unicam.cs.pa2021.f1;

public interface Acceleration<N extends Number> {

    /**
     * Indica lo spostamento verticale di un veicolo.
     *
     * @return lo spostamento verticale di un veicolo.
     */
    N getX();

    /**
     * Indica lo spostamento orizzontale di un veicolo.
     *
     * @return lo spostamento orizzontale di un veicolo.
     */
    N getY();
}
