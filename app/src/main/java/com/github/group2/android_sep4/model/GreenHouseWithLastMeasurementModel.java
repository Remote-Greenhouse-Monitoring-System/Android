package com.github.group2.android_sep4.model;

import com.github.group2.android_sep4.model.Measurement;

public class GreenHouseWithLastMeasurementModel {
    private long greenHouseId;
    private String name;
    private Measurement lastMeasurement;

    public GreenHouseWithLastMeasurementModel(long greenHouseId, String name, Measurement lastMeasurement) {
        this.greenHouseId = greenHouseId;
        this.name = name;
        this.lastMeasurement = lastMeasurement;
    }

    public long getGreenHouseId() {
        return greenHouseId;
    }

    public void setGreenHouseId(long greenHouseId) {
        this.greenHouseId = greenHouseId;
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
