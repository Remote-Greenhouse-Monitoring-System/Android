package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.networking.GreenhouseApi;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.ServiceGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenhouseRepositoryImpl implements GreenhouseRepository {

    private MutableLiveData<String> errorMessage;
    private MutableLiveData<String> successMessage;
    private MutableLiveData<List<Greenhouse>> allGreenhouses;
    private MutableLiveData<Greenhouse> selectedGreenhouse;

    private List<String> allDevices;



    private static GreenhouseRepository instance;
    private static Lock lock = new ReentrantLock();

    private GreenhouseApi greenhouseApi;

    private GreenhouseRepositoryImpl() {
        greenhouseApi = ServiceGenerator.getGreenHouseApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        allGreenhouses = new MutableLiveData<>();
        selectedGreenhouse = new MutableLiveData<>();
        allDevices = new ArrayList<>();
        allDevices.add("0004A30B00251001");
        allDevices.add("0004A30B00E8355E");
    }

    public static GreenhouseRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new GreenhouseRepositoryImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public void searchAllGreenhousesForAUser(long userId) {
        resetInfo();

        Call<List<Greenhouse>> call = greenhouseApi.getGreenHouses(userId);
        call.enqueue(new Callback<List<Greenhouse>>() {

            @Override
            public void onResponse(Call<List<Greenhouse>> call, Response<List<Greenhouse>> response) {
                List<Greenhouse> GreenhouseWithLastMeasurementModelList = response.body();
                allGreenhouses.setValue(GreenhouseWithLastMeasurementModelList);
            }

            @Override
            public void onFailure(Call<List<Greenhouse>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });

    }

    @Override
    public void searchGreenhousesWithLastMeasurement(long userId) {
        Call<List<Greenhouse>> call = greenhouseApi.getGreenHouses(userId);
        call.enqueue(new Callback<List<Greenhouse>>() {
            @Override
            public void onResponse(Call<List<Greenhouse>> call, Response<List<Greenhouse>> response) {
                if (response.isSuccessful()) {
                    allGreenhouses.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<List<Greenhouse>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public LiveData<List<Greenhouse>> getGreenhouseWithLastMeasurement() {
        return allGreenhouses;
    }

    @Override
    public void addGreenhouse(long userId, Greenhouse greenhouse) {
        resetInfo();
        Call<Greenhouse> call = greenhouseApi.addGreenhouse(userId, greenhouse);
        call.enqueue(new Callback<Greenhouse>() {
            @Override
            public void onResponse(Call<Greenhouse> call, Response<Greenhouse> response) {
                if (response.isSuccessful()) {

                    Greenhouse body = response.body();
                    allGreenhouses.getValue().add(body);
                    allGreenhouses.setValue(allGreenhouses.getValue());
                    successMessage.setValue("Greenhouse added successfully");
                } else {
                   setError(response);
                }
            }

            @Override
            public void onFailure(Call<Greenhouse> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public void deleteGreenhouse(long greenhouseId) {
        resetInfo();
        Call<Greenhouse> call = greenhouseApi.deleteGreenhouse(greenhouseId);
        call.enqueue(new Callback<Greenhouse>() {
            @Override
            public void onResponse(Call<Greenhouse> call, Response<Greenhouse> response) {
                if (response.isSuccessful()) {
                    Greenhouse body = response.body();
                    allGreenhouses.getValue().removeIf(greenhouse -> greenhouse.getId() == body.getId());
                    allGreenhouses.setValue(allGreenhouses.getValue());
                    successMessage.setValue("Greenhouse deleted successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Greenhouse> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public void updateGreenhouse(Greenhouse greenhouse) {
        resetInfo();
        Call<Greenhouse> call = greenhouseApi.updateGreenhouse(greenhouse);
        call.enqueue(new Callback<Greenhouse>() {
            @Override
            public void onResponse(Call<Greenhouse> call, Response<Greenhouse> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse updated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Greenhouse> call, Throwable t) {

            }
        });
    }

    @Override
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    @Override
    public void setSelectedGreenhouse(Greenhouse greenhouse) {
        this.selectedGreenhouse.setValue(greenhouse);
    }

    @Override
    public LiveData<Greenhouse> getSelectedGreenhouse() {
        return selectedGreenhouse;
    }

    @Override
    public void resetInfo() {
        errorMessage.setValue(null);
        successMessage.setValue(null);
    }

    @Override
    public List<String> getDevices() {
        return allDevices;
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
