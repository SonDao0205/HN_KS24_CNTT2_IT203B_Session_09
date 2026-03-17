package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public class Car extends Vehicle {

    public Car(String id) {
        super(id, 2.0, 0, VehicleType.CAR);
    }

    @Override
    public void move() {
        System.out.println(vehicleType + " #" + getId() + " đang chạy trên làn đường.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}