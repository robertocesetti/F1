package it.unicam.cs.pa2021.f1;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interfaccia che rappresenta l'intero piano di gara.
 */
public interface RacingPlan<P extends Position, R extends RacingVehicle<P>> {

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
    default List<P> getTrackLayout(){
        return getAllPositions().parallelStream()
                .filter(p -> p.getStatus().equals(StatusPosition.IN))
                .collect(Collectors.toList());
    }

    /**
     * Aggiunge un veicolo in una posizione nella griglia di partenza.
     *
     * @param racingVehicle veicolo che viene aggiunta.
     * @param position posizione nella griglia di partenza.
     * @return true se la posizione e' corretta, false altrimenti.
     */
    boolean addVehicle(R racingVehicle, P position);

    /**
     * Restituisce tutte le posizioni raggiungibili dal veicolo.
     *
     * @param vehiclePosition la posizione corrente del veicolo.
     * @return la lista di posizioni raggiungibili.
     */
    List<Position> nextPositions(P vehiclePosition);

    /**
     * Effettua lo spostamento del veicolo nella posizione indicata.
     *
     * @param racingVehicle il veicolo che vogliamo spostare.
     * @param nextPosition la posizione in cui vogliamo spostare il veicolo.
     */
    default void toPosition(R racingVehicle, P nextPosition) {
        List<Position> nearPositions = nextPositions(racingVehicle.getPosition());
        if (nearPositions.contains(nextPosition)) {
            racingVehicle.setPosition(nextPosition);
        }
    }

}
