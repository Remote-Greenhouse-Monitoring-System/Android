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


    public void searchMeasurement(long greenHouseId, int amount) {

        repository.searchMeasurement(greenHouseId, amount);
    }

    public void searchLastMeasurement(long greenHouseId) {
        repository.searchLastMeasurement(greenHouseId);

    }

    public void searchAllMeasurementsPerHour(long greenHouseId, int hours) {
        repository.searchAllMeasurementsPerHour(greenHouseId, hours);

    }

    public void searchAllMeasurementPerDays(long greenHouseId, int days) {

        repository.searchAllMeasurementPerDays(greenHouseId, days);
    }

    public void searchAllMeasurementPerMonth(long greenHouseId, int month, int year) {

        repository.searchAllMeasurementPerMonth(greenHouseId, month, year);
    }

    public void searchAllMeasurementPerYear(long greenHouseId, int year) {

        repository.searchAllMeasurementPerYear(greenHouseId, year);
    }


    // In the activity observe these values...
    public LiveData<String> getError() {
        return repository.getError();

    }

    public LiveData<List<Measurement>> getSearchedMeasurementList() {

        return repository.getSearchedMeasurementList();
    }

    public LiveData<Measurement> getSearchedMeasurement() {
        return repository.getSearchedMeasurement();

    }
}
