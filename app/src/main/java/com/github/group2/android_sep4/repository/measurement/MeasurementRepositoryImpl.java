package com.github.group2.android_sep4.repository.measurement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.networking.MeasurementApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasurementRepositoryImpl implements  MeasurementRepository{

    private static MeasurementRepository instance;
    private static Lock lock = new ReentrantLock();


    private MeasurementApi api;

    private MutableLiveData<List<Measurement>> searchedMeasurementsList;
    private MutableLiveData<Measurement> searchedMeasurement;
    private MutableLiveData<String> error;


    private MeasurementRepositoryImpl(){
        // Private for singleton

        api = ServiceGenerator.getMeasurementApi();
        error = new MutableLiveData<>();
        searchedMeasurement = new MutableLiveData<>();
        searchedMeasurementsList = new MutableLiveData<>();

    }

    public static MeasurementRepository getInstance(){
        if (instance ==null){
            synchronized (lock){
                if (instance == null){
                    instance = new MeasurementRepositoryImpl();
                }
            }
        }
        return instance;

    }




    @Override
    public void searchMeasurement(long greenHouseId, int amount) {
        Call<List<Measurement>> call = api.getMeasurements(greenHouseId, amount);

        call.enqueue(new Callback<List<Measurement>>() {
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if (response.isSuccessful()){
                    searchedMeasurementsList.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

                error.setValue(t.getMessage());
            }
        });

    }

    @Override
    public void searchLastMeasurement(long greenHouseId) {
        Call<Measurement> call = api.getLastMeasurement(greenHouseId);


        call.enqueue(new Callback<Measurement>() {
            @Override
            public void onResponse(Call<Measurement> call, Response<Measurement> response) {
                if (response.isSuccessful()){
                    searchedMeasurement.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Measurement> call, Throwable t) {
                error.setValue(t.getMessage());
            }
        });

    }

    @Override
    public void searchAllMeasurementsPerHour(long greenHouseId, int hours) {
        ArrayList<Measurement> measurements= new ArrayList<>();
        measurements.add(new Measurement(1,1,30,100,222,10000,"2022-11-01 00:00:00"));
        measurements.add(new Measurement(2,1,25,75,189,11500,"2022-11-01 00:10:00"));
        measurements.add(new Measurement(3,1,22,44,190,12900,"2022-11-01 00:20:00"));
        measurements.add(new Measurement(4,1,27,50,105,10010,"2022-11-01 00:30:00"));
        measurements.add(new Measurement(5,1,21,90,178,15000,"2022-11-01 00:40:00"));
        searchedMeasurementsList.setValue(measurements);
       /* Call<List<Measurement>> call = api.getAllMeasurementsPerHour(greenHouseId, hours);

        call.enqueue(new Callback<List<Measurement>>() {
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if (response.isSuccessful()){
                    searchedMeasurementsList.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

                error.setValue(t.getMessage());
            }
        });*/

    }

    @Override
    public void searchAllMeasurementPerDays(long greenHouseId, int days) {

        ArrayList<Measurement> measurements= new ArrayList<>();
        measurements.add(new Measurement(1,1,30,100,222,10000,"2022-11-01 00:00:00"));
        measurements.add(new Measurement(2,1,25,75,189,11500,"2022-11-01 01:00:00"));
        measurements.add(new Measurement(3,1,22,44,190,12900,"2022-11-01 02:00:00"));
        measurements.add(new Measurement(4,1,27,50,105,10010,"2022-11-01 03:00:00"));
        measurements.add(new Measurement(5,1,21,90,178,15000,"2022-11-01 04:00:00"));
        searchedMeasurementsList.setValue(measurements);
       /* Call<List<Measurement>> call = api.getAllMeasurementPerDays(greenHouseId, days);

        call.enqueue(new Callback<List<Measurement>>() {
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if (response.isSuccessful()){
                    searchedMeasurementsList.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

                error.setValue(t.getMessage());
            }
        });*/

    }

    @Override
    public void searchAllMeasurementPerMonth(long greenHouseId, int month, int year) {


        ArrayList<Measurement> measurements= new ArrayList<>();
        measurements.add(new Measurement(1,1,30,100,222,10000,"2022-11-01 00:00:00"));
        measurements.add(new Measurement(2,1,25,75,189,11500,"2022-11-02 01:00:00"));
        measurements.add(new Measurement(3,1,22,44,190,12900,"2022-11-03 02:00:00"));
        measurements.add(new Measurement(4,1,27,50,105,10010,"2022-11-04 03:00:00"));
        measurements.add(new Measurement(5,1,21,90,178,15000,"2022-11-05 04:00:00"));
        measurements.add(new Measurement(6,1,30,100,222,10000,"2022-11-06 05:00:00"));
        measurements.add(new Measurement(7,1,25,75,189,11500,"2022-11-07 06:00:00"));
        measurements.add(new Measurement(8,1,22,44,190,12900,"2022-11-08 07:00:00"));
        measurements.add(new Measurement(9,1,27,50,105,10010,"2022-11-09 08:00:00"));
        measurements.add(new Measurement(10,1,21,90,178,15000,"2022-11-10 09:00:00"));
        measurements.add(new Measurement(11,1,30,100,222,10000,"2022-11-11 10:00:00"));
        searchedMeasurementsList.setValue(measurements);
       /* Call<List<Measurement>> call = api.getAllMeasurementPerMonth(greenHouseId, month, year);
        call.enqueue(new Callback<List<Measurement>>() {
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if (response.isSuccessful()){
                    searchedMeasurementsList.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

                error.setValue(t.getMessage());
            }
        });*/
    }

    @Override
    public void searchAllMeasurementPerYear(long greenHouseId, int year) {
        Call<List<Measurement>> call = api.getAllMeasurementPerYear(greenHouseId, year);

        call.enqueue(new Callback<List<Measurement>>() {
            @Override
            public void onResponse(Call<List<Measurement>> call, Response<List<Measurement>> response) {
                if (response.isSuccessful()){
                    searchedMeasurementsList.setValue(response.body());
                }
                else{
                    String errorMessage = "Error :"+ response.code()+ " " +
                            response.errorBody().toString();
                    error.setValue(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Measurement>> call, Throwable t) {

                error.setValue(t.getMessage());
            }
        });

    }

    @Override
    public LiveData<String> getError() {
        return error;
    }

    @Override
    public MutableLiveData<List<Measurement>> getSearchedMeasurementList() {
        return searchedMeasurementsList;
    }

    @Override
    public MutableLiveData<Measurement> getSearchedMeasurement() {
        return searchedMeasurement;
    }
}
