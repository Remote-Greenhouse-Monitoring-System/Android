package com.github.group2.android_sep4.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.GreenHouse;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepositoryImpl;

import java.util.List;

public class HomeViewModel extends ViewModel {


    private GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();


    public LiveData<List<GreenHouse>> getGreenHouseList() {
       return repository.getAllGreenHousesForAnUser();
    }

    public void searchAllGreenHouses(long userId) {
        repository.searchAllGreenHousesForAnUser(userId);
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return repository.getSuccessMessage();
    }
}
