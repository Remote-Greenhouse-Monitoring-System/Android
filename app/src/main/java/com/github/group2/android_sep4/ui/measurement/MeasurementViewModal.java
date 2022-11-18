package com.github.group2.android_sep4.ui.measurement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepository;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepositoryImpl;

import java.util.List;

public class MeasurementViewModal extends ViewModel {


    private MeasurementRepository repository;


    public MeasurementViewModal() {
        repository = MeasurementRepositoryImpl.getInstance();
    }


    void searchMeasurement(long greenHouseId, int amount) {

        repository.searchMeasurement(greenHouseId, amount);
    }

    void searchLastMeasurement(long greenHouseId) {
        repository.searchLastMeasurement(greenHouseId);

    }

    void searchAllMeasurementsPerHour(long greenHouseId, int hours) {
        repository.searchAllMeasurementsPerHour(greenHouseId, hours);

    }

    void searchAllMeasurementPerDays(long greenHouseId, int days) {

        repository.searchAllMeasurementPerDays(greenHouseId, days);
    }

    void searchAllMeasurementPerMonth(long greenHouseId, int month, int year) {

        repository.searchAllMeasurementPerMonth(greenHouseId, month, year);
    }

    void searchAllMeasurementPerYear(long greenHouseId, int year) {

        repository.searchAllMeasurementPerYear(greenHouseId, year);
    }



    // In the activity observe these values...
    LiveData<String> getError() {
        return repository.getError();

    }

    LiveData<List<Measurement>> getSearchedMeasurementList() {

        return repository.getSearchedMeasurementList();
    }

    LiveData<Measurement> getSearchedMeasurement() {
        return repository.getSearchedMeasurement();

    }
}
