package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.networking.ThresholdApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.ThresholdRepository;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThresholdRepositoryImpl implements ThresholdRepository {

    private static ThresholdRepository instance;
    private static Lock lock = new ReentrantLock();
    private MutableLiveData<String> errorMessage, successMessage;
    private MutableLiveData<Threshold> searchedThreshold;

    private ThresholdApi thresholdApi;

    private ThresholdRepositoryImpl() {
        thresholdApi = ServiceGenerator.getThresholdApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        searchedThreshold = new MutableLiveData<>();
    }

    public static ThresholdRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ThresholdRepositoryImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public void searchThreshold(long plantProfileId) {
        Call<Threshold> call = thresholdApi.getThreshold(plantProfileId);
        call.enqueue(new Callback<Threshold>() {
            @Override
            public void onResponse(Call<Threshold> call, Response<Threshold> response) {
                if (response.isSuccessful()) {
                    searchedThreshold.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Threshold> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public LiveData<Threshold> getSearchedThreshold() {
        return searchedThreshold;
    }

    @Override
    public void updateThreshold(long plantProfileId, Threshold threshold) {
        Call<Void> call = thresholdApi.updateThreshold(plantProfileId, threshold);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Threshold updated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
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