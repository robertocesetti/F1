package it.unicam.cs.pa2021.f1.controller;

/**
 * Interfaccia che definisce il salvataggio del piano di gara da una sorgente esterna.
 *
 */
public interface RacingPlanReader<N> {

    /**
     * Definisce le posizioni di un piano di gara.
     */
    void RacingPlanPositions(N height, N width);
}
