package it.unicam.cs.pa2021.f1.controller;

/**
 * Interfaccia che difinisce l'implementazione del motore di gioco.
 */
public interface GameEngine<R, P> {

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata se la posizione e' accessibile.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition  la posizione in cui vogliamo spostare il veicolo.
     */
    void moveRacingVehicle(R racingVehicle, P nextPosition);


}
