package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.MeasurementRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.MeasurementRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final MeasurementRepository measurementRepository = MeasurementRepositoryImpl.getInstance();

    public LiveData<String> getErrorMessage() {
        return greenhouseRepository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return greenhouseRepository.getSuccessMessage();
    }

    public LiveData<List<Greenhouse>> getGreenHousesWithLastMeasurement() {
        return greenhouseRepository.getGreenhouseWithLastMeasurement();
    }

    public void setSelectedGreenhouse(Greenhouse greenhouse) {
        greenhouseRepository.setSelectedGreenhouse(greenhouse);
    }

    public void addGreenhouse(long userId, Greenhouse greenhouse){
        greenhouseRepository.addGreenhouse(userId, greenhouse);
    }

    public void resetInfo() {
        greenhouseRepository.resetInfo();
    }

    public LiveData<User> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void searchAllGreenhousesForAUser(long userId) {
        greenhouseRepository.searchAllGreenhousesForAUser(userId);
    }

    public List<String> getDevices() {
        return greenhouseRepository.getDevices();
    }

    public LiveData<Measurement> getLastMeasurement(long id) {
        return measurementRepository.getLastMeasurement(id);

    }
}
