package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public class Motorbike extends StandardVehicle {
    public Motorbike(String id) {
        super(id, 3, 1, false, VehicleType.MOTORBIKE);
    }
    @Override
    public void move() {
    }
}
