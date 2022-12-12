package com.github.group2.android_sep4.model;

public class Threshold {
    private long id;
    private float temperatureMax, temperatureMin, humidityMax, humidityMin, co2Max, co2Min;


    public Threshold(float temperatureMax, float temperatureMin, float humidityMax, float humidityMin, float co2Max, float co2Min) {
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
        this.co2Max = co2Max;
        this.co2Min = co2Min;

    }

    public Threshold() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(float maxHumidity) {
        this.humidityMax = maxHumidity;
    }

    public float getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(float humidityMin) {
        this.humidityMin = humidityMin;
    }

    public float getCo2Max() {
        return co2Max;
    }

    public void setCo2Max(float co2Max) {
        this.co2Max = co2Max;
    }

    public float getCo2Min() {
        return co2Min;
    }

    public void setCo2Min(float co2Min) {
        this.co2Min = co2Min;
    }



    @Override
    public String toString() {
        return "Threshold{" +
                "maxTemperature=" + temperatureMax +
                ", minTemperature=" + temperatureMin +
                ", maxHumidity=" + humidityMax +
                ", minHumidity=" + humidityMin +
                ", maxCo2=" + co2Max +
                ", minCo2=" + co2Min +
                '}';
    }


}
