package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenhouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.callback.ApiCallback;
import com.github.group2.android_sep4.repository.implementaion.GreenhouseRepositoryImpl;
import com.github.group2.android_sep4.model.GreenhouseWithLastMeasurementModel;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;

public class GreenhouseViewModel extends ViewModel
{
    private final GreenhouseRepository greenhouseRepository = GreenhouseRepositoryImpl.getInstance();
    private final PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();

    public LiveData<GreenhouseWithLastMeasurementModel> getSelectedGreenhouse()
    {
        return greenhouseRepository.getSelectedGreenhouse();
    }

    public void setSelectedGreenHouse(GreenhouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        greenhouseRepository.setSelectedGreenhouse(greenHouseWithLastMeasurementModel);
    }

    public LiveData<PlantProfile> getActivePlantProfile() {
        return plantProfileRepository.getActivatedPlantProfile();
    }

//    public void removeActivePlantProfile() {
//        if(plantProfile == null) {
//        }
//        else {
//        plantProfile.setValue(null);
//        }
//    }

    public void setGreenhouseId(long greenhouseId) {

    }
//
//    public void setPlantProfile(PlantProfile plantProfile, long greenHouseId) {
//        //this.plantProfile.setValue(plantProfile);
//        plantProfileRepository.activatePlantProfile(plantProfile.getId(), greenHouseId, new ApiCallback() {
//            @Override
//            public void onResponse(boolean success) {
//
//            }
//        });
//    }

    public void deactivatePlantProfile() {
        long greenhouseId = greenhouseRepository.getSelectedGreenhouse().getValue().getId();
        plantProfileRepository.deactivatePlantProfile(greenhouseId);
    }
}
