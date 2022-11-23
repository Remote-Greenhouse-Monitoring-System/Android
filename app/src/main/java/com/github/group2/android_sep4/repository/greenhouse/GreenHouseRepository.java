package com.github.group2.android_sep4.repository.greenhouse;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

import java.util.List;

public interface GreenHouseRepository {
    LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseList();

    void searchAllGreenHouses();
}
