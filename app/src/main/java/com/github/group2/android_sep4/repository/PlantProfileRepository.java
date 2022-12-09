package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;


import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.callback.ApiCallback;

import java.util.List;

public interface PlantProfileRepository {
    void addPlantProfile(long userId, PlantProfile plantProfile);
    void deletePlantProfile(long plantProfileId);
    void updatePlantProfile(PlantProfile plantProfile);
    void searchPlantProfilesForUser(long userId);

    LiveData<List<PlantProfile>> getPlantProfilesForUser();

    void searchAllPlantProfiles();
    LiveData<List<PlantProfile>> getAllPlantProfiles();

    void searchPlantProfile(long plantProfileId);
    LiveData<PlantProfile> getPlantProfile();

    void activatePlantProfile(long plantProfileId, long greenHouseId, ApiCallback callback);

    void searchActivatedPlantProfile(long greenHouseId);
    LiveData<PlantProfile> getActivatedPlantProfile();

    LiveData<String> getErrorMessage();
    LiveData<String> getSuccessMessage();

    void deactivatePlantProfile(long greenHouseId);
}
