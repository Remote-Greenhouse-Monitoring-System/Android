package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.implementation.MockGreenHouseRepositoryImpl;
import com.github.group2.android_sep4.view.GreenHouseWithLastMeasurementModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private GreenHouseRepository repository = MockGreenHouseRepositoryImpl.getInstance();

    public LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseList() {
        return repository.getGreenHouseList();
    }

    //This makes no sense?
    public void searchAllGreenHouses() {
        repository.searchAllGreenHouses();
    }
}
