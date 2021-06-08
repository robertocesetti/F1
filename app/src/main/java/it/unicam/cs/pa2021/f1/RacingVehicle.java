package it.unicam.cs.pa2021.f1;

import java.util.List;

/**
 * Interfaccia che definisce l'implementazione di un veicolo.
 */
public interface RacingVehicle<P extends Position> {

    /**
     * Restituisce l'identificativo del veicolo.
     *
     * @return l'identificativo del veicolo.
     */
    int getId();


    /**
     * Restituisce la posizione del veicolo.
     *
     * @return la posizione del veicolo.
     */
    P getPosition();

    /**
     * Aggiorna la posizione del veicolo alla posizione indicata.
     *
     * @param position la nuova posizione del veicolo.
     */
    void setPosition(P position);

    /**
     * Restituisce la traiettoria del veicolo.
     *
     * @return la traiettoria del veicolo, ossia la lista delle posizioni occupate.
     */
    List<P> getTrajectory();

}
