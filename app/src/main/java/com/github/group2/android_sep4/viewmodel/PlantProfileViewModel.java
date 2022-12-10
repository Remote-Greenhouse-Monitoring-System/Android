package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.ThresholdRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.ThresholdRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

import java.util.List;

public class PlantProfileViewModel {

    private PlantProfileRepository plantProfileRepository;
    private ThresholdRepository thresholdRepository;
    private UserRepository userRepository;

    public PlantProfileViewModel()
    {
        plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
        thresholdRepository = ThresholdRepositoryImpl.getInstance();
        userRepository = UserRepositoryImpl.getInstance();
    }


    /* PLANT PROFILE METHODS*/

    public void addPlantProfile(long userId, PlantProfile plantProfile)
    {
        plantProfileRepository.addPlantProfile(userId,plantProfile);
    }

    public void deletePlantProfile(long plantProfileId)
    {
        plantProfileRepository.deletePlantProfile(plantProfileId);
    }

    public void updatePlantProfile(PlantProfile plantProfile)
    {
        plantProfileRepository.updatePlantProfile(plantProfile);
    }

    public void searchPlantProfilesForUser(long userId)
    {
        plantProfileRepository.searchPlantProfilesForUser(userId);
    }

    public LiveData<List<PlantProfile>> getPlantProfileForUser()
    {
        return plantProfileRepository.getPlantProfileForUser();
    }

    public void searchAllPlantProfiles()
    {
        plantProfileRepository.searchAllPlantProfiles();
    }

    public LiveData<List<PlantProfile>> getAllPlantProfiles()
    {
        return plantProfileRepository.getAllPlantProfiles();
    }

    public void searchPlantProfile(long plantProfileId)
    {
        plantProfileRepository.searchPlantProfile(plantProfileId);
    }

    public LiveData<PlantProfile> getPlantProfile()
    {
        return plantProfileRepository.getPlantProfile();
    }

    public void activatePlantProfile(long plantProfileId, long greenHouseId)
    {
        plantProfileRepository.activatePlantProfile(plantProfileId,greenHouseId);
    }

    public void searchActivatedPlantProfile(long greenHouseId)
    {
        plantProfileRepository.searchActivatedPlantProfile(greenHouseId);
    }

    public LiveData<PlantProfile> getActivatedPlantProfile()
    {
        return plantProfileRepository.getActivatedPlantProfile();
    }

    public LiveData<String> getErrorMessagePlantProfile()
    {
        return plantProfileRepository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessagePlantProfile()
    {
        return plantProfileRepository.getSuccessMessage();
    }


    /* THRESHOLD METHODS*/

    public void searchThreshold( long plantProfileId)
    {
        thresholdRepository.searchThreshold(plantProfileId);
    }

    public LiveData<Threshold> getSearchedThreshold()
    {
        return thresholdRepository.getSearchedThreshold();
    }

    public void updateThreshold( long plantProfileId, Threshold threshold)
    {
        thresholdRepository.updateThreshold(plantProfileId,threshold);
    }

    public LiveData<String> getErrorMessageThreshold()
    {
        return thresholdRepository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessageThreshold()
    {
        return thresholdRepository.getSuccessMessage();
    }

    /* USER METHODS*/

    public LiveData<User> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

}
