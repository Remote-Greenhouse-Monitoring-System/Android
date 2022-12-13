package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.Greenhouse;

import java.util.List;

public interface GreenhouseRepository {

    void searchAllGreenhousesForAUser(long userId);

    void searchGreenhousesWithLastMeasurement(long userId);
    LiveData<List<Greenhouse>> getGreenhouseWithLastMeasurement();

    void addGreenhouse(long userId, Greenhouse greenhouse);

    void deleteGreenhouse(long greenhouseId);

    void updateGreenhouse(Greenhouse greenhouse);

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();

    void setSelectedGreenhouse(Greenhouse greenhouse);

    LiveData<Greenhouse> getSelectedGreenhouse();

    void resetInfo();

    List<String> getDevices();

}
