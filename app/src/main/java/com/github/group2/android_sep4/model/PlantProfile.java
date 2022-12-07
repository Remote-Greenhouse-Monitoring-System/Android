package com.github.group2.android_sep4.model;

public class PlantProfile {

    private int id,optimalLight;
    private String name, description;
    private float optimalTemp, optimalHumidity,optimalCo2;
    private Threshold threshold;

    public PlantProfile() {

    }

    public PlantProfile(int id, String name, String description, float optimalTemp, float optimalHumidity, float optimalCo2, Threshold threshold) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.optimalTemp = optimalTemp;
        this.optimalHumidity = optimalHumidity;
        this.optimalCo2 = optimalCo2;
        this.threshold = threshold;
    }

    public PlantProfile(int id, String name, String description, float optimalTemp, float optimalHumidity, float optimalCo2,int optimalLight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.optimalTemp = optimalTemp;
        this.optimalHumidity = optimalHumidity;
        this.optimalCo2 = optimalCo2;
        this.optimalLight = optimalLight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "PlantProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", optimalTemp=" + optimalTemp +
                ", optimalHumidity=" + optimalHumidity +
                ", optimalCo2=" + optimalCo2 +
                '}';
    }
}
