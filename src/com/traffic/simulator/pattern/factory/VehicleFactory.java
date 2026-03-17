package com.traffic.simulator.pattern.factory;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.*;

import java.util.Random;

public class VehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createRandom(String id) {
        int chance = random.nextInt(100);
        // 40% Motorbike, 35% Car, 15% Truck, 10% Ambulance
        if (chance < 40) {
            return new Motorbike(id);
        } else if (chance < 75) {
            return new Car(id);
        } else if (chance < 90) {
            return new Truck(id);
        } else {
            return new Ambulance(id);
        }
    }

}