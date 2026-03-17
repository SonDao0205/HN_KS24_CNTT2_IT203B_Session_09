package com.traffic.simulator.entity.vehicle;
import com.traffic.simulator.entity.VehicleType;

public class Ambulance extends PriorityVehicle {
    public Ambulance(String id) {
        super(id, 4, 1, true, VehicleType.AMBULANCE);
    }

    public void activateSiren() {
        System.out.println("Xe cứu thương " + id + " đang hú còi ưu tiên");
    }

    @Override
    public void move() {
        activateSiren();
    }
}
