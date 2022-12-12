package com.github.group2.android_sep4.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measurement {

    private long id;
    private long greenHouseId;
    private float temperature;
    private float humidity;
    private float co2;
    private long light;
    private String timestamp;


    public Measurement() {
        // Do not delete the no-arg constructor.
        // The framework needs this for conversion from JSON.
    }

    public Measurement(long id, long greenHouseId, float temperature, float humidity, float co2, long light, String timestamp) {
        this.id = id;
        this.greenHouseId = greenHouseId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.light = light;
        this.timestamp = timestamp;
    }

    public Measurement( float temperature, float humidity, float co2)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
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

    public long getLight() {
        return light;
    }

    public void setLight(long light) {
        this.light = light;
    }

    public String  getDateTimeAsString() {
        return timestamp;
    }



    /**
     * It takes a string in the format of "yyyy-MM-dd HH:mm:ss" and returns a LocalDateTime object
     *
     * @return A LocalDateTime object.
     */
    public LocalDateTime getDateTimeAsLocalDateTime() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timestamp, dateTimeFormatter);
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isAllZeros() {
        return temperature == 0
                && humidity == 0
                && co2 == 0
                && light == 0;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", greenHouseId=" + greenHouseId +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", light=" + light +
                ", dateTime='" + timestamp + '\'' +
                '}';
    }
}
