package it.unicam.cs.pa2021.f1.controller;

import java.io.FileNotFoundException;

/**
 * Interfaccia che definisce il salvataggio del tracciato da una sorgente esterna.
 *
 * @param <P> Il tipo di dati letti dalla sorgente esterna i quali formeranno il tracciato.
 */
public interface TrackReader {

    /**
     * Permette la lettura di un file di testo.
     *
     * @throws FileNotFoundException
     */
    void readFile() throws FileNotFoundException;
}
