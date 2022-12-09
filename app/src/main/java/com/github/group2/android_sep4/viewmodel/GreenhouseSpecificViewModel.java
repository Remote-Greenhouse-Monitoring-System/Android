package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.Measurement;
import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.repository.GreenHouseRepository;
import com.github.group2.android_sep4.repository.PlantProfileRepository;
import com.github.group2.android_sep4.repository.implementaion.GreenHouseRepositoryImpl;
import com.github.group2.android_sep4.model.GreenHouseWithLastMeasurementModel;
import com.github.group2.android_sep4.repository.implementaion.PlantProfileRepositoryImpl;

public class GreenhouseSpecificViewModel extends ViewModel
{
    private final GreenHouseRepository repository = GreenHouseRepositoryImpl.getInstance();
    private MutableLiveData<PlantProfile> plantProfile;
    private PlantProfileRepository plantProfileRepository = PlantProfileRepositoryImpl.getInstance();


    public LiveData<GreenHouseWithLastMeasurementModel> getSelectedGreenhouse()
    {
        return repository.getSelectedGreenhouse();
    }

    public void setSelectedGreenHouse(GreenHouseWithLastMeasurementModel greenHouseWithLastMeasurementModel) {
        repository.setSelectedGreenHouse(greenHouseWithLastMeasurementModel);


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
