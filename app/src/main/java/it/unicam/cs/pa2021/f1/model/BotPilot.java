package it.unicam.cs.pa2021.f1.model;

/**
 * Implementazione di default di un pilota bot.
 */
public class BotPilot implements Pilot<DefaultRacingVehicle>{

    private final String name;
    private final DefaultRacingVehicle racingVehicle;

    /**
     * Costruttore del pilota bot.
     *
     * @param name il nome del bot.
     * @param racingVehicle il veicolo del bot.
     */
    public BotPilot(String name, DefaultRacingVehicle racingVehicle) {
        this.name = name;
        this.racingVehicle = racingVehicle;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DefaultRacingVehicle getRacingVehicle() {
        return this.racingVehicle;
    }
    
}
