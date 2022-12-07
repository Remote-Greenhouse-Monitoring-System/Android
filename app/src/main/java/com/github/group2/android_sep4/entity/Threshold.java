package com.github.group2.android_sep4.entity;

public class Threshold {
    private float maxTemperature, minTemperature, maxHumidity, minHumidity, maxCo2, minCo2;
    private int durationLight, offsetLight;

    public Threshold(float maxTemperature, float minTemperature, float maxHumidity, float minHumidity, float maxCo2, float minCo2, int durationLight, int offsetLight) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.maxHumidity = maxHumidity;
        this.minHumidity = minHumidity;
        this.maxCo2 = maxCo2;
        this.minCo2 = minCo2;
        this.durationLight = durationLight;
        this.offsetLight = offsetLight;
    }

    public Threshold() {
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(float maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public float getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(float minHumidity) {
        this.minHumidity = minHumidity;
    }

    public float getMaxCo2() {
        return maxCo2;
    }

    public void setMaxCo2(float maxCo2) {
        this.maxCo2 = maxCo2;
    }

    public float getMinCo2() {
        return minCo2;
    }

    public void setMinCo2(float minCo2) {
        this.minCo2 = minCo2;
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

    @Override
    public String toString() {
        return "Threshold{" +
                "maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", maxHumidity=" + maxHumidity +
                ", minHumidity=" + minHumidity +
                ", maxCo2=" + maxCo2 +
                ", minCo2=" + minCo2 +
                ", durationLight=" + durationLight +
                ", offsetLight=" + offsetLight +
                '}';
    }
}
