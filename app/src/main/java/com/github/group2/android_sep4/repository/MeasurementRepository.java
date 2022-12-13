package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.Measurement;

import java.util.List;

public interface MeasurementRepository {

    void searchMeasurement( long greenhouseId, int amount);

    void searchLastMeasurement( long greenhouseId);

    void searchAllMeasurementsPerHour(long greenhouseId, int hours );

    void searchAllMeasurementPerDays( long greenhouseId, int days);

    void searchAllMeasurementPerMonth( long greenhouseId,int month,   int year);

    void searchAllMeasurementPerYear( long greenhouseId, int year);

    LiveData<String> getError();

    LiveData<List<Measurement>> getSearchedMeasurementList();

    LiveData<Measurement> getSearchedMeasurement();
}
