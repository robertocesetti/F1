package it.unicam.cs.pa2021.f1.model;

import java.util.List;
import java.util.Set;

/**
 * Interfaccia che definisce l'implementazione di un veicolo.
 */
public interface RacingVehicle<N, P, A> {

    /**
     * Restituisce l'identificativo del veicolo.
     *
     * @return l'identificativo del veicolo.
     */
    N getId();

    /**
     * Restituisce l'accelerazione del veicolo.
     *
     * @return l'accelerazione del veicolo.
     */
    A getAcceleration();

    /**
     * Imposta l'accelerazione del veicolo all'accelerazione passata.
     *
     * @param acceleration l'accelerazione che deve essere impostata al veicolo.
     */
    void setAcceleration(A acceleration);

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
     * @return la traiettoria del veicolo, ossia la lista delle posizioni occupate durante la gara.
     */
    List<P> getTrajectory();

    /**
     * Imposta la traiettoria alla lista di posizioni passata.
     *
     * @param trajectory la lista di posizioni passata.
     */
    void setTrajectory(List<DefaultPosition> trajectory);

    /**
     * Aggiorna la traiettoria del veicolo aggiungendo la posizione passata.
     *
     * @param position la posizione da aggiungere alla lista di posizioni che formano la traiettoria.
     */
    void updateTrajectory(P position);

    /**
     * Restituisce informazioni sulla posizione del veicolo che si puo' trovare
     * in pista o fuori pista.
     *
     * @return true se il veicolo si trova fuori pista, false altrimenti.
     */
    boolean isOut();
}
