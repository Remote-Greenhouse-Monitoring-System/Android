package com.github.group2.android_sep4.ui.plantProfile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.repository.plant_profile.PlantProfileRepository;
import com.github.group2.android_sep4.repository.plant_profile.PlantProfileRepositoryImpl;

import java.util.List;

public class PlantProfileViewModel extends ViewModel {

    private PlantProfileRepository repository;

    public PlantProfileViewModel()
    {
        repository = PlantProfileRepositoryImpl.getInstance();
    }

    public void createPlantProfile(PlantProfile plantProfile)
    {
        repository.createPlantProfile(plantProfile);
    }

    public void removePlantProfile(int id)
    {
        repository.removePlantProfile(id);
    }

    public void updatePlantProfile(PlantProfile plantProfile)
    {
         repository.updatePlantProfile(plantProfile);
    }


    public void getPlantProfilesForUser(int uId)
    {
        repository.getPlantProfilesForUser(uId);
    }


    public void getPlantProfiles()
    {
        repository.getPlantProfiles();
    }

    public void getPlantProfileById(int id)
    {
         repository.getPlantProfileById(id);
    }

    public void activatePlantProfile(int pId, int gId)
    {
        repository.activatePlantProfile(pId,gId);
    }

    public void getActivatedPlantProfiles(int gId)
    {
        repository.getActivatedPlantProfiles(gId);
    }

    public MutableLiveData<PlantProfile> getSearchedPlantProfile()
    {
        return repository.getSearchedPlantProfile();
    }

    MutableLiveData<List<PlantProfile>> getPlantProfilesListForUser()
    {
        return repository.getPlantProfilesListForUser();
    }

    MutableLiveData<List<PlantProfile>> getAllPlantProfilesList()
    {
        return repository.getAllPlantProfilesList();
    }

    MutableLiveData<List<PlantProfile>> getActivatedPlantProfilesList()
    {
        return repository.getActivatedPlantProfilesList();
    }
}
