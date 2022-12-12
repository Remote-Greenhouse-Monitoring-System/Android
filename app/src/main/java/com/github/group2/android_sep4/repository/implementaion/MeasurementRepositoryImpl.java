package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.networking.MeasurementApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.MeasurementRepository;


import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasurementRepositoryImpl implements MeasurementRepository {
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
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
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

        Call<List<Measurement>> call = api.getAllMeasurementsPerHour(greenHouseId, hours);

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
    public void searchAllMeasurementPerDays(long greenHouseId, int days) {
        Call<List<Measurement>> call = api.getAllMeasurementsPerDay(greenHouseId, days);

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
    public void searchAllMeasurementPerMonth(long greenHouseId, int month, int year) {
        Call<List<Measurement>> call = api.getAllMeasurementsPerMonth(greenHouseId, month, year);
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
    public void searchAllMeasurementPerYear(long greenHouseId, int year) {
        Call<List<Measurement>> call = api.getAllMeasurementsPerYear(greenHouseId, year);

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

    //TODO: Consider removing the mutable live data
    @Override
    public MutableLiveData<Measurement> getSearchedMeasurement() {
        return searchedMeasurement;
    }
}
