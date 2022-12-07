package com.github.group2.android_sep4.repository.plant_profile;

import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.PlantProfile;

import java.util.List;

public interface PlantProfileRepository {

    void createPlantProfile(PlantProfile plantProfile);
    void removePlantProfile(int id);
    void updatePlantProfile(PlantProfile plantProfile);
    void getPlantProfilesForUser(int uId);
    void getPlantProfiles();
    void getPlantProfileById(int id);
    void activatePlantProfile(int pId, int gId);
    void getActivatedPlantProfiles(int gId);
    MutableLiveData<PlantProfile> getSearchedPlantProfile();
    MutableLiveData<List<PlantProfile>> getPlantProfilesListForUser();
    MutableLiveData<List<PlantProfile>> getAllPlantProfilesList();
    MutableLiveData<List<PlantProfile>> getActivatedPlantProfilesList();
}
