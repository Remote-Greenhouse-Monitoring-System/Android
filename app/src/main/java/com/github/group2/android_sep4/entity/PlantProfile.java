package com.github.group2.android_sep4.entity;

public class PlantProfile {

    private long id;
    private String name, description;
    private float optimalTemp, optimalHumidity, optimalCo2, optimalLight;

    public PlantProfile() {

    }

    public PlantProfile(long id, String name, String description, float optimalTemp, float optimalHumidity, float optimalCo2, float optimalLight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.optimalTemp = optimalTemp;
        this.optimalHumidity = optimalHumidity;
        this.optimalCo2 = optimalCo2;
        this.optimalLight = optimalLight;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getOptimalTemp() {
        return optimalTemp;
    }

    public void setOptimalTemp(float optimalTemp) {
        this.optimalTemp = optimalTemp;
    }

    public float getOptimalHumidity() {
        return optimalHumidity;
    }

    public void setOptimalHumidity(float optimalHumidity) {
        this.optimalHumidity = optimalHumidity;
    }

    public float getOptimalCo2() {
        return optimalCo2;
    }

    public void setOptimalCo2(float optimalCo2) {
        this.optimalCo2 = optimalCo2;
    }

    public float getOptimalLight() {
        return optimalLight;
    }

    public void setOptimalLight(float optimalLight) {
        this.optimalLight = optimalLight;
    }
}
