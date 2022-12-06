package com.github.group2.android_sep4.repository.threshold;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.Threshold;
import com.github.group2.android_sep4.networking.ThresholdApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThresholdRepositoryImpl implements ThresholdRepository{

    private static ThresholdRepository instance;
    private static Lock lock = new ReentrantLock();
    private MutableLiveData<String> errorMessage, successMessage;
    private MutableLiveData<Threshold> searchedThreshold;



    private ThresholdApi thresholdApi;
    private ThresholdRepositoryImpl() {
        thresholdApi = ServiceGenerator.getThresholdApi();
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

    }

    @Override
    public LiveData<Threshold> getSearchedThreshold() {
        return null;
    }

    @Override
    public void updateThreshold(long plantProfileId, Threshold threshold) {

    }

    @Override
    public LiveData<String> getErrorMessage() {
        return null;
    }

    @Override
    public LiveData<String> getSuccessMessage() {
        return null;
    }
}
