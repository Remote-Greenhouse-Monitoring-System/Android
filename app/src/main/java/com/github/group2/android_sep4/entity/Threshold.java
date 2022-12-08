package com.github.group2.android_sep4.entity;

public class Threshold {

    private long id;
    private float temperatureMin, humidityMin, co2Min;
    private float temperatureMax, humidityMax, co2Max;
    private float lightMin, lightMax;

    public Threshold() {
        // Required for the framework to work
    }


    public Threshold(long id, float temperatureMin, float humidityMin, float co2Min, float lightMin, float temperatureMax, float humidityMax, float co2Max, float lightMax) {
        this.id = id;
        this.temperatureMin = temperatureMin;
        this.humidityMin = humidityMin;
        this.co2Min = co2Min;
        this.temperatureMax = temperatureMax;
        this.humidityMax = humidityMax;
        this.co2Max = co2Max;
        this.lightMin = lightMin;
        this.lightMax = lightMax;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(float humidityMin) {
        this.humidityMin = humidityMin;
    }

    public float getCo2Min() {
        return co2Min;
    }

    public void setCo2Min(float co2Min) {
        this.co2Min = co2Min;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(float humidityMax) {
        this.humidityMax = humidityMax;
    }

    public float getCo2Max() {
        return co2Max;
    }

    public void setCo2Max(float co2Max) {
        this.co2Max = co2Max;
    }

    public float getLightMin() {return lightMin;
    }

    public void setLightMin(float lightMin) {this.lightMin = lightMin;
    }

    public float getLightMax() {return lightMax;
    }

    public void setLightMax(float lightMax) {this.lightMax = lightMax;
    }
}
