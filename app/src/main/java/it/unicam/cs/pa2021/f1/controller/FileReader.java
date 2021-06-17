package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;

import java.io.IOException;

/**
 * Interfaccia che definisce la lettura di un piano di gara da un file esterno.
 */
public interface FileReader<N> {

    /**
     * Restituisce un piano di gara.
     */
    DefaultRacingPlan getRacingPlan() throws IOException;
}
