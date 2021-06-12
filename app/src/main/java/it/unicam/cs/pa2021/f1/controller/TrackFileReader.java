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
        int planHeight = 0;
        int planWidth = 0;
        List<StatusPosition> statusPositions = new ArrayList<>();
        File plan = new File("D:\\Workspace Eclipse\\F1\\app\\src\\main\\resources\\track.txt");
        Scanner scanner = new Scanner(plan);
        while (scanner.hasNextLine()) {
            char[] line = scanner.nextLine().toCharArray();
            planWidth = line.length;
            planHeight++;
            for (char c : line) {
                if (c == 0) statusPositions.add(StatusPosition.OUT);
                else if (c == 1) statusPositions.add(StatusPosition.IN);
                else throw new IllegalArgumentException("Il file contiene dati errati");
            }
        }
        new DefaultRacingPlan(planHeight, planWidth, statusPositions);
    }

}
