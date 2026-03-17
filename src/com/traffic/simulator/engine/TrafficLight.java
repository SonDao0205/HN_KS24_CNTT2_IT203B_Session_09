package com.traffic.simulator.engine;

import com.traffic.simulator.pattern.observer.TrafficObserver;
import com.traffic.simulator.pattern.observer.TrafficSubject;
import com.traffic.simulator.pattern.state.GreenState;
import com.traffic.simulator.pattern.state.TrafficLightState;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrafficLight implements TrafficSubject, Runnable {
    private TrafficLightState currentState;
    private List<TrafficObserver> observers = new CopyOnWriteArrayList<>();

    public TrafficLight() {
        this.currentState = new GreenState();
    }

    // ================= Observer =================
    @Override
    public void subscribe(TrafficObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(TrafficObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (TrafficObserver observer : observers) {
            observer.onTrafficLightChanged(currentState);
        }
    }

    // ================= State =================
    public void changeState(TrafficLightState newState) {
        this.currentState = newState;
        notifyObservers();
    }

    public boolean isGreen() {

    }

    @Override
    public void run() {

    }
}
