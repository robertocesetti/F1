package it.unicam.cs.pa2021.f1;

import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interfaccia che rappresenta l'intero piano di gara.
 */
public interface RacingPlan<P extends Position, R extends RacingVehicle> {

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
    default List<P> getTrackLayout() {
        return getAllPositions().parallelStream()
                .filter(p -> p.getStatus().equals(StatusPosition.IN))
                .collect(Collectors.toList());
    }

    /**
     * Aggiunge un veicolo in una posizione nella griglia di partenza.
     *
     * @param racingVehicle veicolo che viene aggiunta.
     * @param position      posizione nella griglia di partenza.
     * @return true se la posizione e' corretta, false altrimenti.
     */
    boolean addVehicleToGrid(R racingVehicle, P position);

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata se la posizione è accessibile.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition  la posizione in cui vogliamo spostare il veicolo.
     */
    void toPosition(R racingVehicle, P nextPosition);
}
