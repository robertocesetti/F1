package it.unicam.cs.pa2021.f1;

import java.util.List;

/**
 * Interfaccia che rappresenta l'implementazione di una qualsiasi auto.
 */
public interface Car {

    /**
     * Restituisce l'identificativo dell'auto.
     *
     * @return l'identificativo dell'auto.
     */
    int getId();

    /**
     * Restituisce il tracciato sul quale è presente l'auto.
     *
     * @return il tracciato sul quale è presente l'auto.
     */
    Track getTrack();

    /**
     * Restituisce la posizione dell'auto.
     *
     * @return la posizione dell'auto.
     */
    Positions getPosition();

    /**
     * Aggiorna la posizione dell'auto alla posizione indicata.
     *
     * @param position la nuova posizione dell'auto.
     */
    void setPosition(Positions position);

    /**
     * Restituisce la traiettoria dell'auto.
     *
     * @return la traiettoria dell'auto, ossia la lista delle posizioni occupate.
     */
    List<Positions> getTrajectory();

    /**
     * Restituisce informazioni sulla posizione dell'auto che si puo' trovare
     * in pista o fuori pista.
     *
     * @return true se l'auto si trova fuori pista, false altrimenti.
     */
    default boolean isOut() {
        if (getTrack().getLayout().get(getPosition()) == null ||
                getTrack().getLayout().get(getPosition()).equals(StatusPosition.OUT)) {
            return true;
        } else {
            return false;
        }
    }
}
