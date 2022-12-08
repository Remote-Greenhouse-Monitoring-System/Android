package com.github.group2.android_sep4.ui.plantProfile;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.repository.plantProfile.PlantProfileRepository;
import com.github.group2.android_sep4.repository.plantProfile.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

public class SelectPlantProfileViewModel extends ViewModel {
    private final PlantProfileRepository repository = PlantProfileRepositoryImpl.getInstance();


    public void setActivePlantProfile(int greenHouseId,int plantProfileId) {
        // TODO: set active plant profile
       repository.activatePlantProfile(15, 3);

    }
}