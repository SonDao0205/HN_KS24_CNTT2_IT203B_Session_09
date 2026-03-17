package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public class Truck extends StandardVehicle {
    public Truck(String id) {
        super(id, 1, 3000, false, VehicleType.TRUCK);
    }
    @Override
    public void move() {}
}
