package it.unicam.cs.pa2021.f1.controller;

/**
 * Interfaccia che difinisce il motore di gioco.
 *
 * @param <R> il tipo che definisce il veicolo.
 * @param <P> il tipo che definisce la posizione.
 * @param <D> il tipo che definisce il piano di gara.
 */
public interface GameEngine<R, P, D> {

    /**
     * Restituisce il piano di gara.
     *
     * @return il piano di gara.
     */
    D getRacingPlan();

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata se la posizione e' accessibile.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition  la posizione in cui vogliamo spostare il veicolo.
     */
    void moveRacingVehicle(R racingVehicle, P nextPosition);


}
