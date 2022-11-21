package com.github.group2.android_sep4.repository.plant_profile;

import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.networking.PlantProfileApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepository;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepositoryImpl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlantProfileRepositoryImpl implements PlantProfileRepository{

    private static PlantProfileRepository instance;
    private static Lock lock = new ReentrantLock();

    private PlantProfileApi api;

    private MutableLiveData<List<PlantProfile>> premadePlantProfilesList;
    private MutableLiveData<PlantProfile> searchedPlantProfile;
    private MutableLiveData<String> error;

    public PlantProfileRepositoryImpl()
    {
        api = ServiceGenerator.getPlantProfileApi();
        error = new MutableLiveData<>();
        premadePlantProfilesList = new MutableLiveData<>();
        searchedPlantProfile = new MutableLiveData<>();
    }

    public static PlantProfileRepository getInstance(){
        if (instance ==null){
            synchronized (lock){
                if (instance == null){
                    instance = new PlantProfileRepositoryImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public PlantProfile createPlantProfile(PlantProfile plantProfile) {
        return null;
    }

    @Override
    public void removePlantProfile(long id) {

    }

    @Override
    public PlantProfile updatePlantProfile(PlantProfile plantProfile) {
        return null;
    }

    @Override
    public List<PlantProfile> getPremadePlantProfiles(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public PlantProfile getPlantProfileById(long id) {
        return null;
    }

    @Override
    public void activatePlantProfile(long id) {

    }
}
