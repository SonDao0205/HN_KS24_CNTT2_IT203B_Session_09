package com.traffic.simulator.entity.vehicle;

import java.util.Scanner;


import com.traffic.simulator.entity.VehicleType;

public class Truck extends StandardVehicle {
    public Truck(String id) {
        // speed=1, crossTime=3s (3000ms)
        super(id, 1, 3000, VehicleType.TRUCK);
    }
}