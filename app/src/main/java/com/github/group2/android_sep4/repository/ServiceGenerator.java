package com.github.group2.android_sep4.repository;

import com.github.group2.android_sep4.networking.GreenhouseApi;
import com.github.group2.android_sep4.networking.MeasurementApi;
import com.github.group2.android_sep4.networking.PlantProfileApi;
import com.github.group2.android_sep4.networking.ThresholdApi;
import com.github.group2.android_sep4.networking.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * It's a singleton class that creates a single instance of the Retrofit object and returns it when the
 * getMeasurementApi() method is called
 */
public class ServiceGenerator {

    private static MeasurementApi measurementApi;
    private static UserApi userApi;
    private static GreenhouseApi greenHouseApi;
    private static PlantProfileApi plantProfileApi;
    private static ThresholdApi thresholdApi;
    private static Lock lock = new ReentrantLock();


    private static String BASE_URL = "https://greenhouse-data.azurewebsites.net";
    public final static String API_KEY = "apiKey:JYP!$jFqqFxmy@TsF6zBNMaSd3Fd&";


    public static MeasurementApi getMeasurementApi() {
        if (measurementApi == null) {
            synchronized (lock) {
                if (measurementApi == null) {
                    measurementApi = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build().create(MeasurementApi.class);

                }
            }
        }
        return measurementApi;
    }


    public static UserApi getUserApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        if (userApi == null) {
            synchronized (lock) {
                if (userApi == null) {
                    userApi = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(UserApi.class);

                }
            }
        }
        return userApi;

    }

    public static GreenhouseApi getGreenHouseApi() {
        if (greenHouseApi == null) {
            synchronized (lock) {
                if (greenHouseApi == null) {
                    greenHouseApi = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build().create(GreenhouseApi.class);

                }

            }
        }
        return greenHouseApi;

    }

    public static PlantProfileApi getPlantProfileApi() {
        if (plantProfileApi == null) {
            synchronized (lock) {
                if (plantProfileApi == null) {
                    plantProfileApi = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build().create(PlantProfileApi.class);

                }

            }
        }
        return plantProfileApi;

    }

    public static ThresholdApi getThresholdApi() {
        if (thresholdApi == null) {
            synchronized (lock) {
                if (thresholdApi == null) {
                    thresholdApi = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build().create(ThresholdApi.class);

                }

            }
        }
        return thresholdApi;

    }

}
