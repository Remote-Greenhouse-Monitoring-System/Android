package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;

import java.util.List;

public interface GreenhouseRepository {

    void searchAllGreenhousesForAUser(long userId);
    LiveData<List<Greenhouse>> getAllGreenhousesForAUser();

    void searchGreenhousesWithLastMeasurement(long userId);
    LiveData<List<GreenhouseWithLastMeasurementModel>> getGreenhouseWithLastMeasurement();


    void addGreenhouse(long userId, Greenhouse greenhouse);

    void deleteGreenhouse(long greenhouseId);

    void updateGreenhouse(Greenhouse greenhouse);

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();

    void setSelectedGreenhouse(GreenhouseWithLastMeasurementModel greenHouseWithLastMeasurementModel);

    LiveData<GreenhouseWithLastMeasurementModel> getSelectedGreenhouse();
}
