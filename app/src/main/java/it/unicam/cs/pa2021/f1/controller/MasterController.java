package it.unicam.cs.pa2021.f1.controller;

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
    void gameSettings(String path) throws IOException;

    /**
     * Restituisce il lettore del piano di gara.
     *
     * @return il lettore del piano di gara.
     */
    P getRacingPlanFileReader();

    /**
     * Configura le impostazioni del giocatore.
     *
     * @param name      del giocatore.
     * @param pilotType il tipo (BOT o PLAYER).
     * @return l'id del veicolo del player.
     */
    int configurePlayer(String name, PilotType pilotType);

    /**
     * Restituisce il motore di gioco.
     *
     * @return il motore di gioco.
     */
    G getGameEngine();

    /**
     * Restituisce il bot controller.
     *
     * @return il bot controller.
     */
    B getBotController();

    /**
     * Restituisce l'arbitro.
     *
     * @return l'arbitro.
     */
    R getReferee();
}
