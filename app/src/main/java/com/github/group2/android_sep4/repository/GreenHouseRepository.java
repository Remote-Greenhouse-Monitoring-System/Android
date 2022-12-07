package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.view.GreenHouseWithLastMeasurementModel;

import java.util.List;

public interface GreenHouseRepository {
    LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseList();

    //This method i don't understand why this is here? shouldn't it be called inside getGreenHouseList?
    void searchAllGreenHouses();
}
