package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingPlan;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;

import java.util.List;
import java.util.Random;

/**
 * Implementazione di default di un controller per il bot.
 * Ha la responsabilita' di calcolare la posizione che il veicolo del bot dovra' raggiungere.
 */
public class BotController implements BotIA {

    private final DefaultGameEngine defaultGameEngine;
    Random random = new Random();

    /**
     * Costruttore del controller del bot.
     *
     * @param racingPlan piano di gara dove agisce il bot.
     */
    public BotController(DefaultRacingPlan racingPlan) {
        defaultGameEngine = new DefaultGameEngine(racingPlan);
    }

    /**
     * Controlla se la posizione passata e' occupata da un veicolo.
     *
     * @param nextPosition la posizione che si vuole controllare.
     * @return true se e' occupata, false altrimenti.
     */
    private boolean checkBusy(DefaultPosition nextPosition) {
        return defaultGameEngine.getRacingPlan().isBusy(nextPosition).isPresent();
    }

    /**
     * Assegna una nuova posizione al veicolo se quella che si vuole raggiungere e' occupata.
     *
     * @param allPositions la lista di tutte le posizioni dalla quale verra' scelta una da raggiungere.
     * @return la posizione assegnata.
     */
    private DefaultPosition assignPosition(List<DefaultPosition> allPositions) {
        DefaultPosition nextPosition = allPositions.get(random.nextInt(allPositions.size()));
        while (checkBusy(nextPosition)) {
            allPositions.remove(nextPosition);
            nextPosition = allPositions.get(random.nextInt(allPositions.size()));
        }
        return nextPosition;
    }

    @Override
    public DefaultPosition botNextPosition(DefaultRacingVehicle racingVehicle) {
        List<DefaultPosition> positions = defaultGameEngine.allNearPosition(racingVehicle);
        return assignPosition(positions);
    }

}
