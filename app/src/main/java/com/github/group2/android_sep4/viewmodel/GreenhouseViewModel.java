package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;

public class GreenhouseViewModel extends ViewModel {
    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();

    public LiveData<GreenhouseWithLastMeasurementModel> getSelectedGreenhouse() {
        return greenhouseRepository.getSelectedGreenhouse();
    }

    public void searchActivatedProfile() {
        GreenhouseWithLastMeasurementModel greenhouseWithLastMeasurementModel = getSelectedGreenhouse().getValue();

        if (greenhouseWithLastMeasurementModel != null) {
            long greenhouseId = greenhouseWithLastMeasurementModel.getId();
            plantProfileRepository.searchActivatedPlantProfile(greenhouseId);
        }
    }

    public LiveData<PlantProfile> getActivePlantProfile() {
        return plantProfileRepository.getActivatedPlantProfile();
    }


    public void deactivatePlantProfile() {
        GreenhouseWithLastMeasurementModel greenhouseWithLastMeasurementModel = getSelectedGreenhouse().getValue();

        if (greenhouseWithLastMeasurementModel != null) {
            long greenhouseId = greenhouseWithLastMeasurementModel.getId();
            plantProfileRepository.deactivatePlantProfile(greenhouseId);
        }
    }
}
