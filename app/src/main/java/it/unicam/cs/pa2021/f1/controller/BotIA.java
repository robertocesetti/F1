package it.unicam.cs.pa2021.f1.controller;

import it.unicam.cs.pa2021.f1.model.DefaultPosition;
import it.unicam.cs.pa2021.f1.model.DefaultRacingVehicle;

/**
 * Interfaccia che definisce l'intelligenza del bot.
 */
public interface BotIA {

    /**
     * Calcola la posizione che il veicolo dovra' raggiungere.
     *
     * @param racingVehicle il l veicolo.
     * @return la posizione che il veicolo dovra' raggiungere.
     */
    DefaultPosition botNextPosition(DefaultRacingVehicle racingVehicle);
}
