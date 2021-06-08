package it.unicam.cs.pa2021.f1;

import java.util.List;

public class DefaultRacingVehicle implements  RacingVehicle<DefaultPosition>{

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public DefaultPosition getPosition() {
        return null;
    }

    @Override
    public void setPosition(DefaultPosition position) {

    }

    @Override
    public List<DefaultPosition> getTrajectory() {
        return null;
    }
}
