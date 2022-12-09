package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;

import java.util.List;

public class SelectPlantProfileViewModel extends ViewModel {
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();

    public LiveData<List<PlantProfile>> getPlantProfilesForUser() {
        return plantProfileRepository.getPlantProfilesForUser();
    }

    public void activatePlantProfile(long plantProfileId) {
        long greenhouseId = greenhouseRepository.getSelectedGreenhouse().getValue().getId();
        plantProfileRepository.activatePlantProfile(plantProfileId, greenhouseId, success -> plantProfileRepository.searchActivatedPlantProfile(greenhouseId));
    }

    public LiveData<GreenhouseWithLastMeasurementModel> getSelectedGreenhouse() {
        return greenhouseRepository.getSelectedGreenhouse();
    }
}
