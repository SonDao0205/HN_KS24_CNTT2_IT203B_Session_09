package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;

public class Ambulance extends PriorityVehicle {

    public Ambulance(String id) {
        super(id, 4.0, 1, VehicleType.AMBULANCE);
    }

    @Override
    public void activateSiren() {
        System.out.println("🚑 [SIREN] " + getId() + ": WOO-WOO-WOO!");
    }

    @Override
    protected long getCrossTime() {
        return 1000;
    }
}