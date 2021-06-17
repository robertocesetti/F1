package it.unicam.cs.pa2021.f1.view;

import java.io.IOException;

/**
 * Interfaccia che definisce la vista.
 */
public interface View {

    /**
     * Permette di avviare la vista.
     */
    void open() throws IOException;

    /**
     * Permette di chiudere la vista.
     */
    void close();

}
