package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public abstract class PriorityVehicle extends Vehicle {

    public PriorityVehicle(String id, int speed, int crossTime, VehicleType type) {
        super(id, speed, crossTime, true, type);
    }

    public abstract void activateSiren();

    @Override
    public void move() {
        activateSiren();
        System.out.println(">>> " + this.toString() + " đang ưu tiên đi thẳng qua ngã tư");

        try {
            Intersection.getInstance().accessIntersection(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
