package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public abstract class PriorityVehicle extends Vehicle {

    public PriorityVehicle(String id, double speed, int priority, VehicleType vehicleType) {
        super(id, speed, priority, vehicleType);
    }

    public abstract void activateSiren();

    @Override
    public void move() {
        activateSiren();

        System.out.println(">>> [ƯU TIÊN] " + vehicleType + " #" + getId() + " đang vượt ngã tư...");

        try {
            long crossTime = getCrossTime();
            Thread.sleep(crossTime);
            System.out.println("<<< [XONG] " + vehicleType + " #" + getId() + " đã ra khỏi ngã tư.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract long getCrossTime();
}