package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.repository.MeasurementRepository;
import com.github.group2.android_sep4.repository.implementation.MeasurementRepositoryImpl;

import java.util.List;

public class MeasurementViewModel extends ViewModel {


    private MeasurementRepository repository;


    public MeasurementViewModel() {
        repository = MeasurementRepositoryImpl.getInstance();
    }


    public void searchMeasurement(long greenHouseId, int amount) {

        repository.searchMeasurement(greenHouseId, amount);
    }

    void searchLastMeasurement(long greenHouseId) {
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

    void searchAllMeasurementPerYear(long greenHouseId, int year) {

        repository.searchAllMeasurementPerYear(greenHouseId, year);
    }



    // In the activity observe these values...
    LiveData<String> getError() {
        return repository.getError();

    }

    public LiveData<List<Measurement>> getSearchedMeasurementList() {

        return repository.getSearchedMeasurementList();
    }

    LiveData<Measurement> getSearchedMeasurement() {
        return repository.getSearchedMeasurement();

    }
}
