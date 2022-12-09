package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

public class MainActivityViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final PlantProfileRepository plantProfileRepository;
    private final GreenhouseRepository greenHouseRepository;

    public MainActivityViewModel(){
        userRepository = UserRepositoryImpl.getInstance();
        plantProfileRepository = PlantProfileRepositoryImpl.getInstance();
        greenHouseRepository = GreenhouseRepositoryImpl.getInstance();
    }

    public LiveData<User> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void init(User userToSave) {
        userRepository.init(userToSave);
        plantProfileRepository.searchPlantProfilesForUser(userToSave.getId());
        greenHouseRepository.searchGreenhousesWithLastMeasurement(userToSave.getId());
    }
}
