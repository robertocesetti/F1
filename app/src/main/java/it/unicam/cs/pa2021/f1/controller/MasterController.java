package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.io.IOException;

/**
 * Interfaccia che definisce il controller principale.
 *
 * @param <P> il tipo che definisce il lettore del file.
 * @param <G> il tipo che definisce il motore di gioco.
 * @param <B> il tipo che definisce il controllore del bot.
 * @param <R> il tipo che definisce l'arbitro.
 */
public interface MasterController<P, G, B, R> {

    /**
     * Configura le impostazioni di gioco.
     *
     * @param path percorso dove e' presente il file contenente il tracciato.
     * @throws IOException se la lettura del file non e' avvenuta correttamente.
     */
    void newGame(String path) throws IOException;

    /**
     * Configura le impostazioni del giocatore.
     *
     * @param name      del giocatore.
     * @param pilotType il tipo (BOT o PLAYER).
     * @return l'id del veicolo del player.
     */
    int configurePlayer(String name, PilotType pilotType);

    /**
     * Restituisce true se qualche veicolo ha raggiunto la fine del tracciato, false altrimenti.
     *
     * @param racingPlan il piano di gara.
     * @return true se qualche veicolo ha raggiunto la fine del tracciato, false altrimenti.
     */
    boolean isFinish(DefaultRacingPlan racingPlan);

}
