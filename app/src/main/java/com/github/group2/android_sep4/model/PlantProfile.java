package com.github.group2.android_sep4.model;

public class PlantProfile {

    private long id;
    private int optimalLight;
    private String name, description;
    private float optimalTemperature, optimalHumidity, optimalCo2;
    private boolean isPreMade;

    public PlantProfile() {
        // Do not delete the no-arg constructor.
        // The framework needs this for conversion from JSON.
    }

    public PlantProfile(long id, String name, String description, float optimalTemperature, float optimalHumidity, float optimalCo2) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.optimalTemperature = optimalTemperature;
        this.optimalHumidity = optimalHumidity;
        this.optimalCo2 = optimalCo2;
    }

    public PlantProfile(long id, String name, String description, float optimalTemperature, float optimalHumidity, float optimalCo2, int optimalLight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.optimalTemperature = optimalTemperature;
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

    public float getOptimalTemperature() {
        return optimalTemperature;
    }

    public void setOptimalTemperature(float optimalTemperature) {
        this.optimalTemperature = optimalTemperature;
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

    public int getOptimalLight() {
        return optimalLight;
    }

    public void setOptimalLight(int optimalLight) {
        this.optimalLight = optimalLight;
    }

    public boolean isPreMade() {
        return isPreMade;
    }

    public void setPreMade(boolean preMade) {
        isPreMade = preMade;
    }

    @Override
    public String toString() {
        return "PlantProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", optimalTemp=" + optimalTemperature +
                ", optimalHumidity=" + optimalHumidity +
                ", optimalCo2=" + optimalCo2 +
                '}';
    }
}
