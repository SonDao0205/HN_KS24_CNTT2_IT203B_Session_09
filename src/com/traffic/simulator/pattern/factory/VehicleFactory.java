package com.traffic.simulator.pattern.factory;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.*;

import java.util.Random;

public class VehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createRandom(String id) {
        int chance = random.nextInt(100);
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

    public static Vehicle crate(VehicleType type, String id) {
        switch (type) {
            case MOTORBIKE: {
                return new Motorbike(id);
            }
            case CAR: {
                return new Car(id);
            }
            case TRUCK: {
                return new Truck(id);
            }
            case AMBULANCE: {
                return new Ambulance(id);
            }
            default:
                throw new IllegalArgumentException("Xe khono hp le");
        }
    }
}