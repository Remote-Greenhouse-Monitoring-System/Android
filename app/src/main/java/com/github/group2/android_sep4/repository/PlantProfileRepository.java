package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;


import com.github.group2.android_sep4.model.PlantProfile;

import java.util.List;

public interface PlantProfileRepository {


    void addPlantProfile(long userId, PlantProfile plantProfile);
    void deletePlantProfile(long plantProfileId);
    void updatePlantProfile(PlantProfile plantProfile);

    void searchPlantProfilesForUser(long userId);
    LiveData<List<PlantProfile>> getPlantProfileForUser();

    void searchAllPlantProfiles();
    LiveData<List<PlantProfile>> getAllPlantProfiles();

    void searchPlantProfile(long plantProfileId);
    LiveData<PlantProfile> getPlantProfile();

    void activatePlantProfile(long plantProfileId, long greenHouseId);

    void searchActivatedPlantProfile(long greenHouseId);
    LiveData<PlantProfile> getActivatedPlantProfile();

    LiveData<String> getErrorMessage();
    LiveData<String> getSuccessMessage();


}
