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

public class AddPlantProfileViewModel extends ViewModel {

    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
    private final ThresholdRepository thresholdRepository = ThresholdRepositoryImpl.getInstance();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private boolean editPressed ; // TODO

    public void addPlantProfile(long userId, PlantProfile plantProfile, Threshold threshold) {
        plantProfileRepository.addPlantProfile(userId, plantProfile, (plantP) -> {
            if(plantP instanceof PlantProfile) {
                thresholdRepository.updateThreshold(((PlantProfile) plantP).getId(), threshold);
            }
        });
    }

    public void deletePlantProfile(long plantProfileId) {
        plantProfileRepository.deletePlantProfile(plantProfileId);
    }

    public LiveData<PlantProfile> getPlantProfile() {
        return plantProfileRepository.getPlantProfile();
    }

    public LiveData<User> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void searchPlantProfilesForUser(long userId) {
        plantProfileRepository.searchPlantProfilesForUser(userId);
    }

    public void setPlantProfileToEdit(PlantProfile plantProfile) {
        plantProfileRepository.setPlantProfileToEdit(plantProfile);
    }
}
