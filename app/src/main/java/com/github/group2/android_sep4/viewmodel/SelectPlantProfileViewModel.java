package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

import java.util.List;

public class SelectPlantProfileViewModel extends ViewModel {
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    public LiveData<List<PlantProfile>> getPlantProfilesForUser() {
        return plantProfileRepository.getPlantProfilesForUser();
    }

    public void activatePlantProfile(long plantProfileId) {
        long greenhouseId = greenhouseRepository.getSelectedGreenhouse().getValue().getId();
        plantProfileRepository.activatePlantProfile(plantProfileId, greenhouseId, success -> plantProfileRepository.searchActivatedPlantProfile(greenhouseId));
    }

    public LiveData<Greenhouse> getSelectedGreenhouse() {
        return greenhouseRepository.getSelectedGreenhouse();
    }

    public LiveData<User> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void searchPlantProfilesForUser(long id) {
        plantProfileRepository.searchPlantProfilesForUser(id);
    }
}
