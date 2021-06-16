package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Optional;

/**
 * Interfaccia che rappresenta l'intero piano di gara.
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
     * Aggiunge un veicolo sulla griglia di partenza, nello specifico nella posizione indicata
     * (primo = 1, secondo = 2 ...).
     *
     * @param racingVehicle il veicolo da corsa da aggiungere.
     * @param gridPosition la posizione di partenza sulla griglia.
     * @return true se il veicolo e' stato aggiunto correttamente, false altrimenti.
     */
    boolean addVehicleToGrid(R racingVehicle, int gridPosition);

}
