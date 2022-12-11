package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.Threshold;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.ThresholdRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.ThresholdRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;


public class EditPlantProfileViewModel extends ViewModel {

    private final UserRepository userRepository= UserRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository= PlantProfileRepositoryImpl.getInstance();
    private final ThresholdRepository thresholdRepository= ThresholdRepositoryImpl.getInstance();


    public void updatePlantProfile(PlantProfile plantProfile) {
        plantProfileRepository.updatePlantProfile(plantProfile);
    }

    public void updateThreshold(int id, Threshold threshold) {
        thresholdRepository.updateThreshold(id, threshold);
    }

    public LiveData<User> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public LiveData<PlantProfile> getPlantProfile() {
        return plantProfileRepository.getPlantProfile();
    }

    public LiveData<Threshold> getSearchedThreshold() {
        return thresholdRepository.getSearchedThreshold();
    }

    public void searchPlantProfilesForUser(long id) {
        plantProfileRepository.searchPlantProfilesForUser(id);
    }

    public LiveData<String> getPlantProfileError() {
        return plantProfileRepository.getErrorMessage();
    }

    public LiveData<String> getPlantProfileSuccess() {
        return plantProfileRepository.getSuccessMessage();
    }

    public void resetMessages() {
        plantProfileRepository.resetMessages();
    }

    public LiveData<PlantProfile> getPlantProfileToEdit() {
        return plantProfileRepository.getPlantProfileToEdit();
    }

    public void searchForThresholds(long plantProfileId) {
        thresholdRepository.searchThreshold(plantProfileId);
    }

    public LiveData<String> getThresholdError() {
        return thresholdRepository.getErrorMessage();
    }

    public LiveData<String> getThresholdSuccess() {
        return thresholdRepository.getSuccessMessage();
    }
}
