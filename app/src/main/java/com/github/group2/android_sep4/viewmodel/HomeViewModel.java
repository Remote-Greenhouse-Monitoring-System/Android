package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();

    public LiveData<String> getErrorMessage() {
        return greenhouseRepository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return greenhouseRepository.getSuccessMessage();
    }

    public LiveData<List<GreenhouseWithLastMeasurementModel>> getGreenHousesWithLastMeasurement() {
        return greenhouseRepository.getGreenhouseWithLastMeasurement();
    }

    public void setSelectedGreenhouse(GreenhouseWithLastMeasurementModel greenhouseWithLastMeasurementModel) {
        greenhouseRepository.setSelectedGreenhouse(greenhouseWithLastMeasurementModel);
    }
}
