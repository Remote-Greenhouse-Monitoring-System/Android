package com.github.group2.android_sep4.ui.plant_profile;

import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.plant_profile.PlantProfileRepository;

import java.util.List;

public class PlantProfileViewModel extends ViewModel {

    private PlantProfileRepository repository;

    public PlantProfileViewModel()
    {

    }

    public PlantProfile createPlantProfile(PlantProfile plantProfile)
    {
       return repository.createPlantProfile(plantProfile);
    }

    public void removePlantProfile(long id)
    {
        repository.removePlantProfile(id);
    }

    public PlantProfile updatePlantProfile(PlantProfile plantProfile)
    {
        return repository.updatePlantProfile(plantProfile);
    }

    public List<PlantProfile> getPremadePlantProfiles(int pageNumber,int pageSize)
    {
        return repository.getPremadePlantProfiles(pageNumber,pageSize);
    }

    public PlantProfile getPlantProfileById(long id)
    {
        return repository.getPlantProfileById(id);
    }

    public void activatePlantProfile(long id)
    {
        repository.activatePlantProfile(id);
    }
}
