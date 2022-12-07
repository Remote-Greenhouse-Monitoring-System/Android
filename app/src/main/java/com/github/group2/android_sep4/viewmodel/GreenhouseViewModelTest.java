package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.github.group2.android_sep4.model.Greenhouse;
import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.implementation.MockGreenHouseRepositoryImpl;
import com.github.group2.android_sep4.view.GreenHouseWithLastMeasurementModel;

import java.util.List;

public class GreenhouseViewModelTest extends ViewModel {
    private GreenHouseRepository repository = MockGreenHouseRepositoryImpl.getInstance();

    public void addGreenhouse(Greenhouse greenhouse){

    }

    public void deleteGreenhouse (int id){

    }

    public void updateGreenhouseWithSelectedPlantProfile(int id, PlantProfile plantProfile){

    }

    public LiveData<List<GreenHouseWithLastMeasurementModel>> getAllGreenHouses() {
        return repository.getGreenHouseList();
    }

    public LiveData<GreenHouseWithLastMeasurementModel> getGreenhouseById(int id)
    {
        // TODO: get from repository
        Measurement measurement1 = new Measurement(1,1,32,25,255,25,"2020-12-12 12:12:12");

        GreenHouseWithLastMeasurementModel model = new GreenHouseWithLastMeasurementModel(1, "Greenhouse 1", measurement1);

        return new LiveData<GreenHouseWithLastMeasurementModel>() {
            @Override
            protected void setValue(GreenHouseWithLastMeasurementModel value) {
                value = model;
            }
        };
    }
}
