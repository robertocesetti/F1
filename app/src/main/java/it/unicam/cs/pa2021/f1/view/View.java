package it.unicam.cs.pa2021.f1.view;

import java.io.IOException;

/**
 * Interfaccia che definisce la vista.
 */
public interface View {

    /**
     * Permette di aprire la vista.
     *
     * @throws IOException se la lettura del file non e' avvenuta correttamente.
     */
    void open() throws IOException;

    /**
     * Permette di chiudere la vista.
     */
    void close();

}
