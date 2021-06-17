package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;

/**
 * Interfaccia che difinisce il motore di gioco.
 */
public interface GameEngine<R, P> {

    /**
     * Restituisce il piano di gara.
     *
     * @return il piano di gara.
     */
    DefaultRacingPlan getRacingPlan();

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata se la posizione e' accessibile.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition  la posizione in cui vogliamo spostare il veicolo.
     */
    void moveRacingVehicle(R racingVehicle, P nextPosition);


}
