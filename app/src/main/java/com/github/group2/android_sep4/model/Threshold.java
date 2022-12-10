package com.github.group2.android_sep4.model;

public class Threshold {
    private float temperatureMax, temperatureMin, humidityMax, humidityMin, co2Max, co2Min, lightMax, lightMin;
    private int durationLight, offsetLight;

    public Threshold(float temperatureMax, float temperatureMin, float humidityMax, float humidityMin, float co2Max, float co2Min, float lightMin, float lightMax, int durationLight, int offsetLight) {
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.humidityMax = humidityMax;
        this.humidityMin = humidityMin;
        this.co2Max = co2Max;
        this.co2Min = co2Min;
        this.lightMax = lightMax;
        this.lightMin = lightMin;
        this.durationLight = durationLight;
        this.offsetLight = offsetLight;
    }

    public Threshold() {
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

    public float getMaxHumidity() {
        return humidityMax;
    }

    public void setMaxHumidity(float maxHumidity) {
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

    public int getDurationLight() {
        return durationLight;
    }

    public void setDurationLight(int durationLight) {
        this.durationLight = durationLight;
    }

    public int getOffsetLight() {
        return offsetLight;
    }

    public void setOffsetLight(int offsetLight) {
        this.offsetLight = offsetLight;
    }

    public float getLightMax() {
        return lightMax;
    }

    public void setLightMax(float lightMax) {
        this.lightMax = lightMax;
    }

    public float getLightMin() {
        return lightMin;
    }

    public void setLightMin(float lightMin) {
        this.lightMin = lightMin;
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
                ", minLight=" + lightMax +
                ", maxLight=" + lightMin +
                ", durationLight=" + durationLight +
                ", offsetLight=" + offsetLight +
                '}';
    }
}
