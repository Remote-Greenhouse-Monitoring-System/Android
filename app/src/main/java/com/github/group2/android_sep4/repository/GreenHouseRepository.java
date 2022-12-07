package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.GreenHouse;
import com.github.group2.android_sep4.model.GreenHouseWithLastMeasurementModel;

import java.util.List;

public interface GreenHouseRepository {


    void searchAllGreenHousesForAnUser(long userId);
    LiveData<List<GreenHouse>> getAllGreenHousesForAnUser();

    void searchGreenHouseWithLastMeasurement(long userId);
    LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseWithLastMeasurement();


    void addGreenHouse(long userId, GreenHouse greenHouse);

    void deleteGreenHouse(long greenHouseId);

    void updateGreenHouse(GreenHouse greenHouse);

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();

    void setSelectedGreenHouse(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel);

    LiveData<GreenHouseWithLastMeasurementModel> getSelectedGreenhouse();
}
