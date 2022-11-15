package com.github.group2.android_sep4.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {

    private long id;
    private long greenHouseId;
    private float temperature;
    private float humidity;
    private float co2;
    private int light;
    private String dateTime;


    public Measurement() {
        // Do not delete the no-arg constructor.
        // The framework needs this for conversion from JSON.
    }

    public Measurement(long id, long greenHouseId, float temperature, float humidity, float co2, int light, String dateTime) {
        this.id = id;
        this.greenHouseId = greenHouseId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.light = light;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGreenHouseId() {
        return greenHouseId;
    }

    public void setGreenHouseId(long greenHouseId) {
        this.greenHouseId = greenHouseId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public String getDateTimeAsString() {
        return dateTime;
    }



    /**
     * It takes a string in the format of "yyyy-MM-dd HH:mm:ss" and returns a LocalDateTime object
     *
     * @return A LocalDateTime object.
     */
    public LocalDateTime getDateTimeAsLocalDateTime() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }


    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
