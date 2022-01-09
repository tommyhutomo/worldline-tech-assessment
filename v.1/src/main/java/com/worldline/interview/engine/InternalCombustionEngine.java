package com.worldline.interview.engine;

import java.util.List;

import com.worldline.interview.utils.FuelType;

public abstract class InternalCombustionEngine{

    private boolean running;
    private int fuelLevel;
    protected List<FuelType> requiredFuelType;
    private FuelType fuelType;

    public InternalCombustionEngine() {
        running = false;
        fuelLevel = 0;
    }

    public void start() {
        if (fuelLevel > 0 && requiredFuelType.contains(fuelType)) {
            running = true;
        } else {
            throw new IllegalStateException("Not able to start engine.");
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void fill(FuelType fuelType, int fuelLevel) {
        if (fuelLevel >= 0 && fuelLevel <= 100) {
            this.fuelLevel = fuelLevel;
        }
        else if (fuelLevel > 100) {
            this.fuelLevel = 100;
        }
        else {
            this.fuelLevel = 0;
        }

        this.fuelType = fuelType;
    }

    protected void setFuelType(List<FuelType> requiredFuelType) {
        this.requiredFuelType=requiredFuelType;
    }
}
