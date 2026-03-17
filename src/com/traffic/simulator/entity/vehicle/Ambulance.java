package com.traffic.simulator.entity.vehicle;


import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.util.LogUtil;

public class Ambulance extends PriorityVehicle {
    public Ambulance(String id) {
        // speed=4, crossTime=1s (1000ms)
        super(id, 4, 1000, VehicleType.AMBULANCE);
    }

    public void activateSiren() {
        LogUtil.log("Hệ thống", "Xe cứu thương " + getId() + " được ưu tiên!", "PRIORITY");
    }

    @Override
    public void move() {
        activateSiren();
        super.move();
    }
}