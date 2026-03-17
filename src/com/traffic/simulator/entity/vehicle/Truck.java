package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public class Truck extends Vehicle {

    public Truck(String id) {
        super(id, 1.0, 0, VehicleType.TRUCK);
    }

    @Override
    public void move() {
        System.out.println(vehicleType + " #" + getId() + " đang bò qua ngã tư...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}