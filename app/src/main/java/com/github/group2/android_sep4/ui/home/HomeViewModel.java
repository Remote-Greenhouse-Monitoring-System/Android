package com.github.group2.android_sep4.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.MockGreenHouseRepositoryImpl;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private GreenHouseRepository repository = MockGreenHouseRepositoryImpl.getInstance();


    public LiveData<List<GreenHouseWithLastMeasurementModel>> getGreenHouseList() {
        return repository.getGreenHouseList();


    }

    public void searchAllGreenHouses() {
        repository.searchAllGreenHouses();
    }
}
