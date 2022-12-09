package com.github.group2.android_sep4.model;

public class GreenhouseWithLastMeasurementModel {
    private long id;
    private String name;
    private Measurement lastMeasurement;

    public GreenhouseWithLastMeasurementModel(long id, String name, Measurement lastMeasurement) {
        this.id = id;
        this.name = name;
        this.lastMeasurement = lastMeasurement;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Measurement getLastMeasurement() {
        return lastMeasurement;
    }

    public void setLastMeasurement(Measurement lastMeasurement) {
        this.lastMeasurement = lastMeasurement;
    }
}
