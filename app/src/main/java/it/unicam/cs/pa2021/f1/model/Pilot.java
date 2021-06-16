package it.unicam.cs.pa2021.f1.model;

/**
 * Interfaccia che definisce l'implementazione di un pilota.
 */
public interface Pilot<R> {

    /**
     * Restituisce il nome del pilota.
     *
     * @return il nome del pilota.
     */
    String getName();

    /**
     * Restituisce il veicolo del pilota.
     *
     * @return il veicolo del pilota.
     */
    R getRacingVehicle();

}
