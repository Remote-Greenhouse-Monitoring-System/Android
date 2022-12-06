package com.github.group2.android_sep4.repository.greenhouse;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.entity.GreenHouse;

import java.util.List;

public interface GreenHouseRepository {


    void searchAllGreenHousesForAnUser(long userId);
    LiveData<List<GreenHouse>> getAllGreenHousesForAnUser();

    void addGreenHouse(long userId, GreenHouse greenHouse);

    void deleteGreenHouse(long greenHouseId);

    void updateGreenHouse(GreenHouse greenHouse);

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();
}
