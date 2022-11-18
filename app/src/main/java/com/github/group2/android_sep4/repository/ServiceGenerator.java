package com.github.group2.android_sep4.repository;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.networking.MeasurementApi;

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
    private static Lock lock = new ReentrantLock();

    private static String BASE_URL_MEASUREMENT = "";




    public static MeasurementApi getMeasurementApi(){
//        if (measurementApi ==null){
//            synchronized (lock){
//                if (measurementApi == null) {
//                    measurementApi = new Retrofit.Builder().baseUrl(BASE_URL_MEASUREMENT)
//                            .addConverterFactory(GsonConverterFactory.create()).build().create(MeasurementApi.class);
//
//                }
//
//            }
//        }
//        return measurementApi;

        return null;
    }
}
