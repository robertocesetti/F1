package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;

import java.io.IOException;

/**
 * Interfaccia che definisce la lettura di un piano di gara da un file esterno.
 */
public interface FileReader<R> {

    /**
     * Restituisce il piano di gara letto dal file.
     *
     * @return il piano di gara letto dal file.
     * @throws IllegalArgumentException se non e' stato impostato un circuito.
     */
    R getRacingPlan() throws IllegalArgumentException;

    /**
     * Imposta il piano di gara dal file letto.
     *
     * @param filePath il percorso del file.
     * @throws IOException se la lettura del file non e' avvenuta correttamente.
     */
    void setRacingPlan(String filePath) throws IOException;
}
