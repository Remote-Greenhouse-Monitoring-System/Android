package com.github.group2.android_sep4.ui.greenhouse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.GreenHouse;
import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepository;
import com.github.group2.android_sep4.repository.greenhouse.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.repository.plantProfile.PlantProfileRepository;
import com.github.group2.android_sep4.repository.plantProfile.PlantProfileRepositoryImpl;
import com.github.group2.android_sep4.ui.home.GreenHouseWithLastMeasurementModel;

public class GreenhouseSpecificViewModel extends ViewModel {
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    private MutableLiveData<PlantProfile> plantProfile;
    private PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();


    public LiveData<GreenHouseWithLastMeasurementModel> getSelectedGreenhouse() {
        // TODO: get from repository
        Measurement measurement1 = new Measurement(1, 1, 32, 25, 255, 25, "2020-12-12 12:12:12");

        GreenHouseWithLastMeasurementModel model = new GreenHouseWithLastMeasurementModel(1, "Greenhouse 1", measurement1);

        return new LiveData<GreenHouseWithLastMeasurementModel>() {
            @Override
            protected void setValue(GreenHouseWithLastMeasurementModel value) {
                value = model;
            }
        };
    }


    public LiveData<PlantProfile> getActivePlantProfile(long greenHouseId) {
        plantProfileRepository.searchActivatedPlantProfile(greenHouseId);
        return plantProfileRepository.getActivatedPlantProfile();

    }

    public void removeActivePlantProfile() {
        if(plantProfile == null) {

        }
        else {
            plantProfile.setValue(null);
        }
    }

    public void setGreenhouseId(long greenhouseId) {

    }

    public void setPlantProfile(PlantProfile plantProfile, long greenHouseId) {
        this.plantProfile.setValue(plantProfile);
        plantProfileRepository.activatePlantProfile(plantProfile.getId(), greenHouseId);
    }
}
