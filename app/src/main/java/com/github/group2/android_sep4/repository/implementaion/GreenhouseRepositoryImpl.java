package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.networking.GreenhouseApi;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.ServiceGenerator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GreenhouseRepositoryImpl implements GreenhouseRepository {

    private MutableLiveData<String> errorMessage;
    private MutableLiveData<String> successMessage;
    private MutableLiveData<List<Greenhouse>> allGreenHouses;
    private MutableLiveData<List<GreenhouseWithLastMeasurementModel>> allGreenHousesWithLastMeasurement;

    private static GreenhouseRepository instance;
    private static Lock lock = new ReentrantLock();

    private GreenhouseApi greenHouseApi;
    private MutableLiveData<GreenhouseWithLastMeasurementModel> selectedGreenhouse;

    private GreenhouseRepositoryImpl() {
        greenHouseApi = ServiceGenerator.getGreenHouseApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        allGreenHouses = new MutableLiveData<>();
        allGreenHousesWithLastMeasurement = new MutableLiveData<>();
        selectedGreenhouse = new MutableLiveData<>();
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
        clearInfo();

        Call<List<Greenhouse>> call = greenHouseApi.getGreenhouses(userId);
        call.enqueue(new Callback<List<Greenhouse>>() {

            @Override
            public void onResponse(Call<List<Greenhouse>> call, Response<List<Greenhouse>> response) {
                List<Greenhouse> greenhouseList = response.body();
                allGreenHouses.setValue(greenhouseList);
            }

            @Override
            public void onFailure(Call<List<Greenhouse>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public LiveData<List<Greenhouse>> getAllGreenhousesForAUser() {
        return allGreenHouses;
    }

    @Override
    public void searchGreenhousesWithLastMeasurement(long userId) {
        Call<List<GreenhouseWithLastMeasurementModel>> call = greenHouseApi.getGreenhouseByUserWithLastMeasurement(userId);
        call.enqueue(new Callback<List<GreenhouseWithLastMeasurementModel>>() {
            @Override
            public void onResponse(Call<List<GreenhouseWithLastMeasurementModel>> call, Response<List<GreenhouseWithLastMeasurementModel>> response) {
                if (response.isSuccessful()) {
                    allGreenHousesWithLastMeasurement.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<List<GreenhouseWithLastMeasurementModel>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public LiveData<List<GreenhouseWithLastMeasurementModel>> getGreenhouseWithLastMeasurement() {
        return allGreenHousesWithLastMeasurement;
    }

    @Override
    public void addGreenhouse(long userId, Greenhouse greenHouse) {
        clearInfo();
        Call<Greenhouse> call = greenHouseApi.addGreenhouse(userId, greenHouse);
        call.enqueue(new Callback<Greenhouse>() {
            @Override
            public void onResponse(Call<Greenhouse> call, Response<Greenhouse> response) {
                if (response.isSuccessful()) {
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
        clearInfo();
        Call<Greenhouse> call = greenHouseApi.deleteGreenhouse(greenhouseId);
        call.enqueue(new Callback<Greenhouse>() {
            @Override
            public void onResponse(Call<Greenhouse> call, Response<Greenhouse> response) {
                if (response.isSuccessful()) {
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
        clearInfo();
        Call<Greenhouse> call = greenHouseApi.updateGreenhouse(greenhouse);
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
                // TODO
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
    public void setSelectedGreenhouse(GreenhouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        this.selectedGreenhouse.setValue(greenHouseWithLastMeasurementModel);
    }

    @Override
    public LiveData<GreenhouseWithLastMeasurementModel> getSelectedGreenhouse() {
        return selectedGreenhouse;
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

    private void clearInfo() {
        errorMessage.setValue(null);
        successMessage.setValue(null);
    }
}
