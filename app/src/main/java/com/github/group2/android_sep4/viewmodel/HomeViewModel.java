package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.GreenHouse;
import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenHouseRepositoryImpl;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();




    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return repository.getSuccessMessage();
    }

    public void searchGreenHousesWithLastMeasurement(long id) {
        repository.searchGreenHouseWithLastMeasurement(id);
    }

    public LiveData<List<GreenHouse>> getGreenHousesWWithLastMeasurement() {
        return repository.getGreenHouseWithLastMeasurement();
    }

    public void addGreenHouse(long userId, GreenHouse greenHouse){
        repository.addGreenHouse(userId, greenHouse);
    }

    public void resetInfos() {
        repository.resetInfos();
    }
}
