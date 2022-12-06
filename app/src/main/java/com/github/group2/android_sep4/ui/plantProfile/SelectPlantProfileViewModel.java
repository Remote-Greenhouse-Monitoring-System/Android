package com.github.group2.android_sep4.ui.plantProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.MockGreenHouseRepositoryImpl;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

public class SelectPlantProfileViewModel extends ViewModel {
    private final GreenHouseRepository repository = MockGreenHouseRepositoryImpl.getInstance();


    public void setActivePlantProfile(int greenHouseId,PlantProfile plantProfile) {
        // TODO: set active plant profile
        //repository.setActivePlantProfile(greenHouseId,plantProfile);
    }
}