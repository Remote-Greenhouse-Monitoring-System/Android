package com.github.group2.android_sep4.model;

public class Greenhouse {

    private long id;
    private String name;
    private Measurement lastMeasurement;
    private String eui;

    public Greenhouse() {
        // Do not delete the no-arg constructor.
        // The framework needs this for conversion from JSON.
    }

    public Greenhouse(long id, String name, Measurement lastMeasurement) {
        this.id = id;
        this.name = name;
        this.lastMeasurement = lastMeasurement;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public String getEui() {
        return eui;
    }

    public Greenhouse(String name, String eui) {
        this.name = name;
        this.eui = eui;
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
