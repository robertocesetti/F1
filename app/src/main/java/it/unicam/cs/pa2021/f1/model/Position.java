package it.unicam.cs.pa2021.f1.model;

import java.util.Set;

/**
 * Interfaccia che definisce l'implementazione di una posizione del tracciato.
 */
public interface Position<S> {

    /**
     * Restituisce lo stato di una posizione del tracciato
     * ossia se si trova all'interno della pista o meno.
     *
     * @return lo stato della posizione.
     */
    S getStatus();

}
