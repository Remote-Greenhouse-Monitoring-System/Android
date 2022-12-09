package com.github.group2.android_sep4.repository.implementaion;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.networking.PlantProfileApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.PlantProfileRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantProfileRepositoryImpl implements PlantProfileRepository {


    private static PlantProfileRepository instance;
    private static Lock lock = new ReentrantLock();

    private MutableLiveData<String> errorMessage;
    private MutableLiveData<String> successMessage;


    private PlantProfileApi plantProfileApi;
    private MutableLiveData<List<PlantProfile>> plantProfilesForUser;
    private MutableLiveData<List<PlantProfile>> allPlantProfiles;
    private MutableLiveData<PlantProfile> searchedPlantProfile;
    private MutableLiveData<PlantProfile> activatedPlantProfile;


    private PlantProfileRepositoryImpl() {
        plantProfileApi = ServiceGenerator.getPlantProfileApi();
        errorMessage = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        plantProfilesForUser = new MutableLiveData<>();
        allPlantProfiles = new MutableLiveData<>();
        searchedPlantProfile = new MutableLiveData<>();
        activatedPlantProfile = new MutableLiveData<>();

    }


    public static PlantProfileRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new PlantProfileRepositoryImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public void addPlantProfile(long userId, PlantProfile plantProfile) {
        Call<PlantProfile> call = plantProfileApi.addPlantProfile(userId, plantProfile);
        call.enqueue(new Callback<PlantProfile>() {

            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Plant profile added successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public void deletePlantProfile(long plantProfileId) {

        Call<PlantProfile> call = plantProfileApi.deletePlantProfile(plantProfileId);
        call.enqueue(new Callback<PlantProfile>() {

            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Plant profile added successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public void updatePlantProfile(PlantProfile plantProfile) {
        Call<PlantProfile> call = plantProfileApi.updatePlantProfile(plantProfile);
        call.enqueue(new Callback<PlantProfile>() {

            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Plant profile updated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });

    }

    @Override
    public void searchPlantProfilesForUser(long userId) {
        Call<List<PlantProfile>> call = plantProfileApi.getPlantProfilesForUser(userId);
        call.enqueue(new Callback<List<PlantProfile>>() {

            @Override
            public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
                if (response.isSuccessful()) {
                    List<PlantProfile> plantProfiles = response.body();
                    plantProfilesForUser.setValue(plantProfiles);
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<List<PlantProfile>> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });

    }

    @Override
    public LiveData<List<PlantProfile>> getPlantProfileForUser() {

        return plantProfilesForUser;
    }

    @Override
    public void searchAllPlantProfiles() {
        Call<List<PlantProfile>> call = plantProfileApi.getPlantProfiles();
        call.enqueue(new Callback<List<PlantProfile>>() {

            @Override
            public void onResponse(Call<List<PlantProfile>> call, Response<List<PlantProfile>> response) {
                if (response.isSuccessful()) {
                    List<PlantProfile> plantProfiles = response.body();
                    allPlantProfiles.setValue(plantProfiles);
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<List<PlantProfile>> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public LiveData<List<PlantProfile>> getAllPlantProfiles() {
        return allPlantProfiles;
    }

    @Override
    public void searchPlantProfile(long plantProfileId) {
        Call<PlantProfile> call = plantProfileApi.getPlantProfile(plantProfileId);
        call.enqueue(new Callback<PlantProfile>() {

            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.isSuccessful()) {
                    PlantProfile plantProfile = response.body();
                    List<PlantProfile> plantProfiles = allPlantProfiles.getValue();
                    searchedPlantProfile.setValue(plantProfile);
                    Log.e("PLANT PROFILE",searchedPlantProfile.getValue().toString());
                    allPlantProfiles.setValue(plantProfiles);
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });


    }

    @Override
    public LiveData<PlantProfile> getPlantProfile() {
        return searchedPlantProfile;
    }

    @Override
    public void activatePlantProfile(long plantProfileId, long greenHouseId) {
        System.out.println("*****************************************\nplant profile id: " +
                plantProfileId + "\ngreenhouse id: " + greenHouseId +
                "*****************************************\n");
        Call<Void> call = plantProfileApi.activatePlantProfile(plantProfileId, greenHouseId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                call.request().url().toString();
                if (response.isSuccessful()) {
                    successMessage.setValue("Plant profile activated successfully");
                    System.out.println("*****************************************\n" +
                            "Plant profile activated successfully" +
                            "*****************************************\n");
                } else {
                    System.out.println("*****************************************\n" +
                            "Plant profile activation failed" +
                            response.toString()+response.errorBody()+
                            "*****************************************\n");
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public void searchActivatedPlantProfile(long greenHouseId) {

        Call<PlantProfile> call = plantProfileApi.getActivatedPlantProfile(greenHouseId);
        call.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if (response.isSuccessful()) {
                    PlantProfile plantProfile = response.body();
                    activatedPlantProfile.setValue(plantProfile);
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });


    }

    @Override
    public LiveData<PlantProfile> getActivatedPlantProfile() {
        return activatedPlantProfile;
    }

    @Override
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    @Override
    public void deactivatePlantProfile(long greenHouseId) {
        Call<Void> call = plantProfileApi.deactivatePlantProfile(greenHouseId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    successMessage.setValue("Plant profile deactivated successfully");
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    private void setError(Response response) {
        String errorMessage = null;
        try {
            errorMessage = "Error :" + response.code() + " " +
                    response.errorBody().string();
        } catch (IOException e) {
            this.errorMessage.setValue("Cannot connect to server");
        }
        this.errorMessage.setValue(errorMessage);


    }
}
