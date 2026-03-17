package com.traffic.simulator.entity.vehicle;

import com.traffic.simulator.entity.VehicleType;

public class Motorbike extends Vehicle {

    public Motorbike(String id) {
        super(id, 3.0, 0, VehicleType.MOTORBIKE);
    }

    @Override
    public void move() {
        System.out.println(vehicleType + " #" + getId() + " đang di chuyển (Tốc độ: " + getSpeed() + ")");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}