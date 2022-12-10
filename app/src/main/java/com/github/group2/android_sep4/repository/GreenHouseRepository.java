package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.GreenHouse;

import java.util.List;

public interface GreenHouseRepository {


    void searchGreenHouseWithLastMeasurement(long userId);
    LiveData<List<GreenHouse>> getGreenHouseWithLastMeasurement();


    void addGreenHouse(long userId, GreenHouse greenHouse);

    void deleteGreenHouse(long greenHouseId);

    void updateGreenHouse(GreenHouse greenHouse);

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();

    void setSelectedGreenHouse(GreenHouse greenHouse);

    LiveData<GreenHouse> getSelectedGreenhouse();

    void resetInfos();
}
