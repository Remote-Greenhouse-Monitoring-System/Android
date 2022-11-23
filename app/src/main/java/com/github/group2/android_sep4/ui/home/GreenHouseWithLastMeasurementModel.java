package com.github.group2.android_sep4.ui.home;

import com.github.group2.android_sep4.entity.Measurement;

public class GreenHouseWithLastMeasurementModel {
    private long greenHouseId;
    private String greenHouseName;
    private Measurement lastMeasurement;

    public GreenHouseWithLastMeasurementModel(long greenHouseId, String greenHouseName, Measurement lastMeasurement) {
        this.greenHouseId = greenHouseId;
        this.greenHouseName = greenHouseName;
        this.lastMeasurement = lastMeasurement;
    }

    public long getGreenHouseId() {
        return greenHouseId;
    }

    public void setGreenHouseId(long greenHouseId) {
        this.greenHouseId = greenHouseId;
    }

    public String getGreenHouseName() {
        return greenHouseName;
    }

    public void setGreenHouseName(String greenHouseName) {
        this.greenHouseName = greenHouseName;
    }

    public Measurement getLastMeasurement() {
        return lastMeasurement;
    }

    public void setLastMeasurement(Measurement lastMeasurement) {
        this.lastMeasurement = lastMeasurement;
    }
}
