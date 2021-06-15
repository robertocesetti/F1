package it.unicam.cs.pa2021.f1.controller;

/**
 * Interfaccia che definisce il salvataggio del piano di gara da una sorgente esterna.
 *
 */
public interface RPlanReader<N> {

    /**
     * Definisce le posizioni di un piano di gara.
     */
    void RPlanPositions(N height, N width);
}
