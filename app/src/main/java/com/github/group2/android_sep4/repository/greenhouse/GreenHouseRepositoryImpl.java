package com.github.group2.android_sep4.repository.greenhouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.GreenHouse;
import com.github.group2.android_sep4.networking.GreenHouseApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenHouseRepositoryImpl implements GreenHouseRepository {


    private MutableLiveData<String> errorMessage;
    private MutableLiveData<String> successMessage;
    private MutableLiveData<List<GreenHouse>> allGreenHouses;


    private static GreenHouseRepository instance;
    private static Lock lock = new ReentrantLock();

    private GreenHouseApi greenHouseApi;

    private GreenHouseRepositoryImpl() {
        greenHouseApi = ServiceGenerator.getGreenHouseApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        allGreenHouses = new MutableLiveData<>();

    }

    public static GreenHouseRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new GreenHouseRepositoryImpl();
                }
            }
        }
        return instance;
    }





    @Override
    public void searchAllGreenHousesForAnUser(long userId) {

        Call<List<GreenHouse>> call = greenHouseApi.getGreenHouses(userId);
        call.enqueue(new Callback<List<GreenHouse>>() {


            @Override
            public void onResponse(Call<List<GreenHouse>> call, Response<List<GreenHouse>> response) {
                List<GreenHouse> greenHouseList = response.body();
                allGreenHouses.setValue(greenHouseList);
            }

            @Override
            public void onFailure(Call<List<GreenHouse>> call, Throwable t) {

                errorMessage.setValue("Cannot connect to the server");
            }
        });

    }

    @Override
    public LiveData<List<GreenHouse>> getAllGreenHousesForAnUser() {
        return allGreenHouses;
    }

    @Override
    public void addGreenHouse(long userId, GreenHouse greenHouse) {
        Call<GreenHouse> call = greenHouseApi.addGreenHouse(userId, greenHouse);
        call.enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse added successfully");
                } else {
                   setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public void deleteGreenHouse(long greenHouseId) {
        Call<GreenHouse> call = greenHouseApi.deleteGreenHouse(greenHouseId);
        call.enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse deleted successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {

            }
        });

    }

    @Override
    public void updateGreenHouse(GreenHouse greenHouse) {
        Call<GreenHouse> call = greenHouseApi.updateGreenHouse(greenHouse);
        call.enqueue(new Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse updated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {

            }
        });

    }

    private void setError(Response response) {
        String errorMessage = null;
        try {
            errorMessage = "Error :" + response.code() + " " +
                    response.errorBody().string();
        } catch (IOException e) {
            this.errorMessage.setValue("Cannot connect to server");
        }
        this.errorMessage.setValue(errorMessage);


    }
}
