package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.callback.ApiCallback;

import java.util.List;

public interface PlantProfileRepository {

    void addPlantProfile(long userId, PlantProfile plantProfile, ApiCallback callback);

    void deletePlantProfile(long plantProfileId);

    void updatePlantProfile(PlantProfile plantProfile, ApiCallback callback);

    void searchPlantProfilesForUser(long userId);

    LiveData<List<PlantProfile>> getPlantProfilesForUser();

    void searchPlantProfile(long plantProfileId);

    LiveData<PlantProfile> getPlantProfile();

    void activatePlantProfile(long plantProfileId, long greenhouseId, ApiCallback callback);

    void searchActivatedPlantProfile(long greenhouseId);

    LiveData<PlantProfile> getActivatedPlantProfile();

    LiveData<String> getErrorMessage();

    LiveData<String> getSuccessMessage();

    void deactivatePlantProfile(long greenhouseId);

    void resetMessages();

    void setPlantProfileToEdit(PlantProfile plantProfile);

    LiveData<PlantProfile> getPlantProfileToEdit();
}
