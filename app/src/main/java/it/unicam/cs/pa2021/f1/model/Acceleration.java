package it.unicam.cs.pa2021.f1.model;

/**
 * Interfaccia che definisce l'implementazione dell'accelerazione di un veicolo
 * ossia il suo spostamento sul piano gara.
 *
 * @param <N> Indica il tipo di dato che si vuole utilizzare per definire le coordinate del vettore accelerazione.
 *           Tale tipo deve essere sottotipo di Number.
 */
public interface Acceleration<N> {

    /**
     * Indica lo spostamento verticale di un veicolo.
     *
     * @return lo spostamento verticale di un veicolo.
     */
    N getX();

    /**
     * Indica lo spostamento orizzontale di un veicolo.
     *
     * @return lo spostamento orizzontale di un veicolo.
     */
    N getY();
}
