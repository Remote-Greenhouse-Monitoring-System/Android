package com.github.group2.android_sep4.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.User;
import com.github.group2.android_sep4.repository.login.MockUserRepositoryImpl;
import com.github.group2.android_sep4.repository.login.UserRepository;
import com.github.group2.android_sep4.repository.login.UserRepositoryImpl;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepository;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepositoryImpl;

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
