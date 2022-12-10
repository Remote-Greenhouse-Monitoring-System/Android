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
    private MutableLiveData<List<Greenhouse>> allGreenhouseWithLastMeasurementModels;
    private MutableLiveData<List<GreenhouseWithLastMeasurementModel>> allGreenhouseWithLastMeasurementModelsWithLastMeasurement;
    
    private static GreenhouseRepository instance;
    private static Lock lock = new ReentrantLock();

    private GreenhouseApi GreenhouseWithLastMeasurementModelApi;
    private MutableLiveData<Greenhouse> selectedGreenhouse;

    private GreenhouseRepositoryImpl() {
        GreenhouseWithLastMeasurementModelApi = ServiceGenerator.getGreenhouseWithLastMeasurementModelApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        GreenhouseWithLastMeasurementModelsWithLastMeasurement = new MutableLiveData<>();
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

        Call<List<Greenhouse>> call = GreenhouseWithLastMeasurementModelApi.getGreenhouses(userId);
        call.enqueue(new Callback<List<Greenhouse>>() {

            @Override
            public void onResponse(Call<List<Greenhouse>> call, Response<List<Greenhouse>> response) {
                List<Greenhouse> GreenhouseWithLastMeasurementModelList = response.body();
                GreenhouseWithLastMeasurementModels.setValue(GreenhouseWithLastMeasurementModelList);
            }

            @Override
            public void onFailure(Call<List<Greenhouse>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });

    }

    private void clearInfo() {
        errorMessage.setValue(null);
        successMessage.setValue(null);

    }

    @Override
    public LiveData<List<Greenhouse>> getAllGreenhousesForAnUser() {
        return GreenhouseWithLastMeasurementModels;
    }

    @Override
    public void searchGreenhouseWithLastMeasurementModelWithLastMeasurement(long userId) {
        Call<List<Greenhouse>> call = GreenhouseWithLastMeasurementModelApi.getGreenhouseWithLastMeasurementModelByUserWithLastMeasurement(userId);
        call.enqueue(new Callback<List<Greenhouse>>() {
            @Override
            public void onResponse(Call<List<Greenhouse>> call, Response<List<Greenhouse>> response) {
                if (response.isSuccessful()) {
                    GreenhouseWithLastMeasurementModelsWithLastMeasurement.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<List<GreenhouseWithLastMeasurementModelWithLastMeasurementModel>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public LiveData<List<GreenhouseWithLastMeasurementModelWithLastMeasurementModel>> getGreenhouseWithLastMeasurementModelWithLastMeasurement() {
        return GreenhouseWithLastMeasurementModelsWithLastMeasurement;
    }

    @Override
    public void addGreenhouseWithLastMeasurementModel(long userId, GreenhouseWithLastMeasurementModel GreenhouseWithLastMeasurementModel) {
        clearInfos();
        Call<GreenhouseWithLastMeasurementModel> call = com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModelApi.addGreenhouseWithLastMeasurementModel(userId, com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel);
        call.enqueue(new Callback<GreenhouseWithLastMeasurementModel>() {
            @Override
            public void onResponse(Call<GreenhouseWithLastMeasurementModel> call, Response<GreenhouseWithLastMeasurementModel> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse added successfully");
                } else {
                   setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenhouseWithLastMeasurementModel> call, Throwable t) {
                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public void deleteGreenhouseWithLastMeasurementModel(long GreenhouseWithLastMeasurementModelId) {
        clearInfos();
        Call<GreenhouseWithLastMeasurementModel> call = GreenhouseWithLastMeasurementModelApi.deleteGreenhouseWithLastMeasurementModel(GreenhouseWithLastMeasurementModelId);
        call.enqueue(new Callback<GreenhouseWithLastMeasurementModel>() {
            @Override
            public void onResponse(Call<GreenhouseWithLastMeasurementModel> call, Response<GreenhouseWithLastMeasurementModel> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse deleted successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenhouseWithLastMeasurementModel> call, Throwable t) {

                errorMessage.setValue("Cannot connect to the server");
            }
        });
    }

    @Override
    public void updateGreenhouseWithLastMeasurementModel(GreenhouseWithLastMeasurementModel GreenhouseWithLastMeasurementModel) {
        clearInfos();
        Call<GreenhouseWithLastMeasurementModel> call = com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModelApi.updateGreenhouseWithLastMeasurementModel(com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel);
        call.enqueue(new Callback<GreenhouseWithLastMeasurementModel>() {
            @Override
            public void onResponse(Call<GreenhouseWithLastMeasurementModel> call, Response<GreenhouseWithLastMeasurementModel> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Greenhouse updated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<GreenhouseWithLastMeasurementModel> call, Throwable t) {

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
    public void setSelectedGreenhouseWithLastMeasurementModel(GreenhouseWithLastMeasurementModelWithLastMeasurementModel GreenhouseWithLastMeasurementModelWithLastMeasurementModel) {
        this.selectedGreenhouse.setValue(GreenhouseWithLastMeasurementModelWithLastMeasurementModel);
    }

    @Override
    public LiveData<GreenhouseWithLastMeasurementModelWithLastMeasurementModel> getSelectedGreenhouse() {
        return selectedGreenhouse;
    }

    @Override
    public void resetInfos() {
        errorMessage.setValue(null);
        successMessage.setValue(null);
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
