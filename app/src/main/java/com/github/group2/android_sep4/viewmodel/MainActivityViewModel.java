package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;
import com.github.group2.android_sep4.repository.MeasurementRepository;
import com.github.group2.android_sep4.repository.implementaion.MeasurementRepositoryImpl;

public class MainActivityViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;

    public MainActivityViewModel(){
        userRepository = UserRepositoryImpl.getInstance();
        measurementRepository = MeasurementRepositoryImpl.getInstance();
    }

    public LiveData<User> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void init(User userToSave) {
        userRepository.init(userToSave);
    }
}
