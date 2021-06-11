package it.unicam.cs.pa2021.f1.model;

import java.util.List;

/**
 * Interfaccia che rappresenta l'intero piano di gara.
 */
public interface RacingPlan<R, P> {

    /**
     * Restituisce la lista dei veicoli presenti sul tracciato.
     *
     * @return la lista dei veicoli presenti sul tracciato.
     */
    List<R> getAllVehicle();

    /**
     * Restituisce la griglia di partenza.
     *
     * @return la griglia di partenza.
     */
    List<P> getGrid();

    /**
     * Restituisce tutte le posizioni del piano di gara.
     *
     * @return tutte le posizioni del piano di gara.
     */
    List<P> getAllPositions();

    /**
     * Restituisce tutte le posizioni che danno forma alla pista.
     *
     * @return tutte le posizioni che danno forma alla pista.
     */
    List<P> getTrackLayout();

    /**
     * Aggiunge un veicolo in una posizione nella griglia di partenza.
     *
     * @param racingVehicle veicolo che viene aggiunta.
     * @param position      posizione nella griglia di partenza.
     * @return true se la posizione e' corretta, false altrimenti.
     */
    boolean addVehicleToGrid(R racingVehicle, P position);

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata se la posizione e' accessibile.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition  la posizione in cui vogliamo spostare il veicolo.
     */
    void toPosition(R racingVehicle, P nextPosition);
}
