package com.traffic.simulator.entity.vehicle;

import java.util.Scanner;


import com.traffic.simulator.entity.VehicleType;

public class Motorbike extends StandardVehicle {
    public Motorbike(String id) {
        // speed=3, crossTime=1s (1000ms)
        super(id, 3, 1000, VehicleType.MOTORBIKE);
    }
}