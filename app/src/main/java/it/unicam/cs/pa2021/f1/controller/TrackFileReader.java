package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.StatusPosition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementazione del salvataggio di un tracciato ottenuto dalla lettura di un file.
 */
public class TrackFileReader implements TrackReader {

    public TrackFileReader() {
    }

    /**
     * Leggo i numeri del file che sono obbligatoriamente 1 o 0.
     * 1 -> pista.
     * 0 -> fuori pista.
     */
    public void readFile() throws FileNotFoundException {
    //TODO
    }

}
