package com.traffic.simulator.entity.vehicle;


import com.traffic.simulator.entity.VehicleType;

public class Car extends StandardVehicle {
    public Car(String id) {
        // speed=2, crossTime=2s (2000ms)
        super(id, 2, 2000, VehicleType.CAR);
    }
}