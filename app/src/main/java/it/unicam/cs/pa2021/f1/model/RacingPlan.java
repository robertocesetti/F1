package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Optional;

/**
 * Interfaccia che definisce l'intero piano di gara.
 *
 * @param <R> Il tipo che si vuole utilizzare per definire il veicolo.
 * @param <P> Il tipo che si vuole utilizzare per definire la posizione.
 */
public interface RacingPlan<R, P> {

    /**
     * Restituisce l'altezza del piano gara.
     *
     * @return l'altezza del piano gara.
     */
    int getHeight();

    /**
     * Restituisce la larghezza del piano gara.
     *
     * @return la larghezza del piano gara.
     */
    int getWidth();

    /**
     * Restituisce tutte le posizioni del piano di gara.
     *
     * @return tutte le posizioni del piano di gara.
     */
    List<P> getAllPositions();

    /**
     * Restituisce la lista dei veicoli presenti sul piano di gara.
     *
     * @return la lista dei veicoli presenti sul piano di gara.
     */
    List<R> getAllVehicles();

    /**
     * Controlla se la posizione passata e' occupata.
     *
     * @param position la posizione che vogliamo sapere se e' occupata.
     * @return il veicolo che la occupa eventualmente.
     */
    Optional<R> isBusy(P position);

    /**
     * Restituisce la griglia di partenza.
     *
     * @return la griglia di partenza.
     */
    List<P> getGrid();

    /**
     * Restituisce il traguardo.
     *
     * @return il traguardo.
     */
    List<P> getFinishLine();

    /**
     * Aggiunge un veicolo sulla griglia di partenza, nello specifico nella posizione indicata
     * (primo = 1, secondo = 2 ...).
     *
     * @param racingVehicle il veicolo da corsa da aggiungere.
     * @param gridPosition  la posizione di partenza sulla griglia.
     */
    void addVehicleToGrid(R racingVehicle, int gridPosition);

}
