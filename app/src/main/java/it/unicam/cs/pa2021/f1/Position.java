package it.unicam.cs.pa2021.f1;

/**
 * Interfaccia che definisce l'implementazione di una posizione del tracciato.
 */
public interface Position<N> extends Acceleration{

    /**
     * Restituisce lo stato di una posizione del tracciato
     * ossia se si trova all'interno della pista o meno.
     *
     * @return lo stato della posizione.
     */
    StatusPosition getStatus();

}
