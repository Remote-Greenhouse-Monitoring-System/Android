package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.Measurement;

import java.util.List;

public interface MeasurementRepository {


    void searchMeasurement( long greenHouseId, int amount);

    void searchLastMeasurement( long greenHouseId);

    void searchAllMeasurementsPerHour(long greenHouseId, int hours );

    void searchAllMeasurementPerDays( long greenHouseId, int days);

    void searchAllMeasurementPerMonth( long greenHouseId,int month,   int year);

    void searchAllMeasurementPerYear( long greenHouseId, int year);

    LiveData<String> getError();
    LiveData<List<Measurement>> getSearchedMeasurementList();
    LiveData<Measurement> getSearchedMeasurement();
}
