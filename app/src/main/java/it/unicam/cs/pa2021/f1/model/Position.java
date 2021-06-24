package it.unicam.cs.pa2021.f1.model;

/**
 * Interfaccia che definisce una posizione del tracciato.
 */
public interface Position {

    /**
     * Restituisce lo stato di una posizione del tracciato
     * ossia se si trova all'interno della pista, fuori, sulla griglia o sul traguardo.
     *
     * @return lo stato della posizione.
     */
    StatusPosition getStatus();

    /**
     * Aggiorna il valore dello stato al valore passato.
     *
     * @param status il nuovo valore dello stato.
     */
    void setStatus(StatusPosition status);

}
