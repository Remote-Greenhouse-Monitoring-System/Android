package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.MeasurementRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.ThresholdRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.MeasurementRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.ThresholdRepositoryImpl;

import java.util.List;

public class MeasurementViewModel extends ViewModel {

    private final MeasurementRepository measurementRepository = MeasurementRepositoryImpl.getInstance();
    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
    private final ThresholdRepository thresholdRepository = ThresholdRepositoryImpl.getInstance();

    public void searchMeasurement(long greenHouseId, int amount) {
        measurementRepository.searchMeasurement(greenHouseId, amount);
    }

    public void searchThreshold(long plantProfileId) {
        thresholdRepository.searchThreshold(plantProfileId);
    }

    public LiveData<Threshold> getSearchedThreshold() {
        return thresholdRepository.getSearchedThreshold();
    }

    public void searchLastMeasurement(long greenHouseId) {
        measurementRepository.searchLastMeasurement(greenHouseId);
    }

    public void searchAllMeasurementsPerHour(long greenHouseId, int hours) {
        measurementRepository.searchAllMeasurementsPerHour(greenHouseId, hours);

    }

    public void searchAllMeasurementPerDays(long greenHouseId, int days) {
        measurementRepository.searchAllMeasurementPerDays(greenHouseId, days);
    }

    public void searchAllMeasurementPerMonth(long greenHouseId, int month, int year) {
        measurementRepository.searchAllMeasurementPerMonth(greenHouseId, month, year);
    }

    public void searchAllMeasurementPerYear(long greenHouseId, int year) {
        measurementRepository.searchAllMeasurementPerYear(greenHouseId, year);
    }

    // In the activity observe these values...
    public LiveData<String> getError() {
        return measurementRepository.getError();
    }

    public LiveData<List<Measurement>> getSearchedMeasurementList() {
        return measurementRepository.getSearchedMeasurementList();
    }

    public LiveData<Measurement> getSearchedMeasurement() {
        return measurementRepository.getSearchedMeasurement();
    }

    public LiveData<PlantProfile> getActivePlantProfile() {
        return plantProfileRepository.getActivatedPlantProfile();
    }

    public LiveData<Greenhouse> getSelectedGreenhouse() {
        return greenhouseRepository.getSelectedGreenhouse();
    }
}
