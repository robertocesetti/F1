package it.unicam.cs.pa2021.f1.model;

/**
 * Implementazione di default di un pilota.
 */
public class DefaultPilot implements Pilot<DefaultRacingVehicle>{

    private final String name;
    private final DefaultRacingVehicle racingVehicle;
    private final PilotType type;

    /**
     * Costruttore del pilota.
     *
     * @param name il nome del pilota.
     * @param racingVehicle il veicolo del pilota.
     */
    public DefaultPilot(String name, DefaultRacingVehicle racingVehicle, PilotType type) {
        this.name = name;
        this.racingVehicle = racingVehicle;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DefaultRacingVehicle getRacingVehicle() {
        return this.racingVehicle;
    }

    @Override
    public PilotType getType() {
        return this.type;
    }

}
