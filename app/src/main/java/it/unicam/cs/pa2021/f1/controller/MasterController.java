package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPilot;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.PilotType;

import java.io.IOException;
import java.util.List;

/**
 * Interfacccia che definisce il controller principale.
 */
public interface MasterController {

    /**
     *    1. E' conosciuto dalla vista ma non la conosce.
     *    2. Conosce il modello.
     *    3. Conosce tutti i cotroller.
     */


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
    RacingPlanReader getRacingPlanFileReader();

    /**
     * Configura le impostazioni del giocatore.
     *
     * @param name del giocatore.
     * @param pilotType il tipo (BOT o PLAYER).
     */
    void configurePlayer(String name, PilotType pilotType);

    /**
     *
     *
     * @param racingPlan
     * @return
     */
    boolean isFinish(DefaultRacingPlan racingPlan);



    /**
     * @return
     */
    DefaultGameEngine getGameEngine();

    /**
     * @return
     */
    BotController getBotController();


}
