package it.unicam.cs.pa2021.f1;

/**
 * Interfaccia che definisce l'implementazione di una posizione del tracciato.
 */
public interface Position {

    /**
     * Restituisce lo stato di una posizione del tracciato
     * ossia se si trova all'interno della pista o meno.
     *
     * @return lo stato della posizione.
     */
    StatusPosition getStatus();

    /**
     * Restituisce informazioni sulla posizione del veicolo che si puo' trovare
     * in pista o fuori pista.
     *
     * @return true se il veicolo si trova fuori pista, false altrimenti.
     */
    boolean isOut(); /**{
        if (getTrack().getLayout().get(getPosition()) == null ||
                getTrack().getLayout().get(getPosition()).equals(StatusPosition.OUT)) {
            return true;
        } else {
            return false;
        }
    }**/

}
