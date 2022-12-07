package com.github.group2.android_sep4.repository.plant_profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.entity.Measurement;
import com.github.group2.android_sep4.entity.PlantProfile;
import com.github.group2.android_sep4.networking.PlantProfileApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.login.UserRepository;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepository;
import com.github.group2.android_sep4.repository.measurement.MeasurementRepositoryImpl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantProfileRepositoryImpl implements PlantProfileRepository{

    private static PlantProfileRepository instance;
    private static Lock lock = new ReentrantLock();

    private PlantProfileApi api;
    private UserRepository userRepository;

    private MutableLiveData<List<PlantProfile>> plantProfilesListForUser;
    private MutableLiveData<List<PlantProfile>> allPlantProfilesList;
    private MutableLiveData<List<PlantProfile>> activatedPlantProfilesList;
    private MutableLiveData<PlantProfile> searchedPlantProfile;
    private MutableLiveData<String> error;

    public PlantProfileRepositoryImpl()
    {
        api = ServiceGenerator.getPlantProfileApi();
        error = new MutableLiveData<>();
        plantProfilesListForUser = new MutableLiveData<>();
        allPlantProfilesList = new MutableLiveData<>();
        activatedPlantProfilesList = new MutableLiveData<>();
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
    public void createPlantProfile(PlantProfile plantProfile) {
        Call<PlantProfile> call = api.createPlantProfile(2,plantProfile);

        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                Log.e("RESPONSE","GOT TO RESPONSE BLOCK");
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");

                    System.out.println("SUCCESS ON REPOSITORY -->" + plantProfile.toString());
                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                Log.e("FAILURE","GOT TO FAILURE BLOCK");
            }
        });
    }

    @Override
    public void removePlantProfile(int id) {
        Call call = api.removePlantProfile(id);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");

                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    @Override
    public void updatePlantProfile(PlantProfile plantProfile) {
        Call<PlantProfile> call = api.updatePlantProfile(plantProfile);

        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");

                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    @Override
    public void getPlantProfilesForUser(int uId) {
        Call<List<PlantProfile>> call = api.getPlantProfilesForUser(uId);

        call.enqueue(new Callback<List<PlantProfile>>() {
            @Override
            public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");
                    plantProfilesListForUser.setValue(response.body());

                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PlantProfile>> call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    @Override
    public void getPlantProfiles()
    {
        Call<List<PlantProfile>> call = api.getPlantProfiles();

        call.enqueue(new Callback<List<PlantProfile>>() {
            @Override
            public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");
                    allPlantProfilesList.setValue(response.body());


                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PlantProfile>> call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    @Override
    public void getPlantProfileById(int id) {
        Call<PlantProfile> call = api.getPlantProfileById(12);

        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                Log.e("RESPONSE","GOT TO RESPONSE BLOCK");
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESSFUL GET PROFILE");

                    searchedPlantProfile.setValue(response.body());
                }else{
                    Log.e("RESPONSE",response.message());
                }

            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                Log.e("FAILURE","GOT TO FAILURE BLOCK");
            }
        });

    }

    @Override
    public void activatePlantProfile(int pId, int gId) {
        Call call = api.activatePlantProfile(pId,gId);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");

                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    @Override
    public void getActivatedPlantProfiles(int gId)
    {
        Call<List<PlantProfile>> call = api.getActivatedPlantProfiles(gId);

        call.enqueue(new Callback<List<PlantProfile>>() {
            @Override
            public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
                if(response.isSuccessful())
                {
                    Log.e("RESPONSE","SUCCESS");
                    activatedPlantProfilesList.setValue(response.body());

                }else{
                    Log.e("RESPONSE", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PlantProfile>> call, Throwable t) {
                Log.e("FAILURE","FAILURE BLOCK");
            }
        });
    }

    public MutableLiveData<PlantProfile> getSearchedPlantProfile() {
        return searchedPlantProfile;
    }

    public MutableLiveData<List<PlantProfile>> getPlantProfilesListForUser() {
        return plantProfilesListForUser;
    }

    public MutableLiveData<List<PlantProfile>> getAllPlantProfilesList() {
        return allPlantProfilesList;
    }

    public MutableLiveData<List<PlantProfile>> getActivatedPlantProfilesList() {
        return activatedPlantProfilesList;
    }
}
