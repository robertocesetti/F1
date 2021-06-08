package it.unicam.cs.pa2021.f1;

import java.util.List;
import java.util.Map;

/**
 * Interfaccia che rappresenta il tracciato di gara.
 */
public interface Track {

    /**
     * Restituisce la griglia di partenza.
     *
     * @return la griglia di partenza.
     */
    List<Positions> getGrid();

    /**
     * Restituisce tutte le posizioni che danno forma al tracciato di gara.
     *
     * @return tutte le posizioni che danno forma al tracciato di gara.
     */
    Map<Positions, StatusPosition> getLayout();

    /**
     * Aggiunge un'auto in una posizione nella griglia di partenza.
     *
     * @param car      auto che viene aggiunta.
     * @param position posizione nella griglia di partenza.
     * @return true se la posizione e' corretta, false altrimenti.
     */
    boolean addCar(Car car, Positions position);


    /**
     * Restituisce tutte le posizioni raggiungibili dall'auto.
     *
     * @param carPosition la posizione corrente dell'auto.
     * @return la lista di posizioni raggiungibili.
     */
    List<Positions> nextPositions(Positions carPosition);

    /**
     * Effettua lo spostamento dell'auto nella posizione indicata.
     *
     * @param car l'auto che vogliamo spostare.
     * @param nextPosition la posizione in cui vogliamo spostare l'auto.
     */
    default void toPosition(Car car, Positions nextPosition) {
        List<Positions> nearPositions = nextPositions(car.getPosition());
        if (nearPositions.contains(nextPosition)) {
            car.setPosition(nextPosition);
        }
    }

}
