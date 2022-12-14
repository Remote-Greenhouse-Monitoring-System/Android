package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.MeasurementRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.MeasurementRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;

public class GreenhouseViewModel extends ViewModel {

    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
    private final MeasurementRepository measurementRepository = MeasurementRepositoryImpl.getInstance();

    public LiveData<Greenhouse> getSelectedGreenhouse() {
        return greenhouseRepository.getSelectedGreenhouse();
    }

    public void searchActivatedProfile() {
        Greenhouse greenhouse = getSelectedGreenhouse().getValue();

        if (greenhouse != null) {
            long greenhouseId = greenhouse.getId();
            plantProfileRepository.searchActivatedPlantProfile(greenhouseId);
        }
    }

    public LiveData<PlantProfile> getActivePlantProfile() {
        return plantProfileRepository.getActivatedPlantProfile();
    }

    public void deactivatePlantProfile() {
        Greenhouse greenhouse = getSelectedGreenhouse().getValue();

        if (greenhouse != null) {
            long greenhouseId = greenhouse.getId();
            plantProfileRepository.deactivatePlantProfile(greenhouseId);
        }
    }

    public void deleteGreenhouse(long greenhouseId) {
        greenhouseRepository.deleteGreenhouse(greenhouseId);
    }

    public LiveData<Measurement> getLastMeasurement( ) {
        return measurementRepository.getSearchedMeasurement();
    }
    public void searchLastMeasurement(long greenhouseId) {
        measurementRepository.searchLastMeasurement(greenhouseId);
    }
}
