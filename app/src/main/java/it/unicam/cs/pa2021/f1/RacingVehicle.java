package it.unicam.cs.pa2021.f1;

import java.util.List;

/**
 * Interfaccia che definisce l'implementazione di un veicolo.
 */
public interface RacingVehicle<P extends Position, A extends Acceleration> {

    /**
     * Restituisce l'identificativo del veicolo.
     *
     * @return l'identificativo del veicolo.
     */
    int getId();

    /**
     * Restituisce l'accelerazione del veicolo.
     *
     * @return il vettore accelerazione del veicolo
     */
    P getAcceleration();

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

    /**
     * Aggiorna la traiettoria del veicolo aggiungendo la posizione passata.
     *
     * @param position la posizione da aggiungere alla lista di posizioni che formano la traiettoria.
     */
    void setTrajectory(P position);

    /**
     * Restituisce informazioni sulla posizione del veicolo che si puo' trovare
     * in pista o fuori pista.
     *
     * @return true se il veicolo si trova fuori pista, false altrimenti.
     */
    boolean isOut();

    /**
     * Restituisce tutte le posizioni raggiungibili dal veicolo.
     *
     * @return la lista di posizioni raggiungibili.
     */
    List<P> nearPositions();

}
