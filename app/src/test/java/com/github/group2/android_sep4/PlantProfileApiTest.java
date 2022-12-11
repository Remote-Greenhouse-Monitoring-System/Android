package com.github.group2.android_sep4;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;


import com.github.group2.android_sep4.model.PlantProfile;
import com.github.group2.android_sep4.networking.PlantProfileApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.viewmodel.PlantProfileViewModel;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlantProfileApiTest {

    private PlantProfileApi plantProfileApi;
    private int responseCode;
    private final CountDownLatch latch = new CountDownLatch(1);


    @Before
    public void setUp()
    {
        plantProfileApi = ServiceGenerator.getPlantProfileApi();
    }

    @Test
    public void testA_ApiNotNull()
    {
        assertNotNull(plantProfileApi);
    }

    @Test
    public void testB_AddPlantProfile() throws InterruptedException {
        Call<PlantProfile> plantProfileCall = plantProfileApi.addPlantProfile(2,new PlantProfile(0, "Potato #1", "Description 1", 20, 25,320,2000));

        plantProfileCall.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if(response.isSuccessful())
                {
                    responseCode = response.code();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

            }
        });
        latch.await();

        assertEquals("Response successful", 200, responseCode);
        assertNotEquals(0, responseCode);
    }



    @Test
    public void testC_EditPlantProfile() throws InterruptedException {
        PlantProfile plantProfile = new PlantProfile(21,"TestEdit","TestEdit",1,1,1,1);
        Call<PlantProfile> plantProfileCall = plantProfileApi.updatePlantProfile(plantProfile);

        plantProfileCall.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if(response.isSuccessful())
                {
                    responseCode = response.code();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

            }
        });
        latch.await();

        assertEquals("Response successful", 200, responseCode);
        assertNotEquals(0, responseCode);
    }

    @Test
    public void testD_DeletePlantProfile() throws InterruptedException {
        Call<PlantProfile> plantProfileCall = plantProfileApi.deletePlantProfile(23);
        plantProfileCall.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                responseCode = response.code();
                if(response.isSuccessful())
                {

                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

            }
        });
        //latch.await();

        assertEquals("Response successful", 200, responseCode);
        assertNotEquals(0, responseCode);
    }

    @Test
    public void testE_ActivatePlantProfile() throws InterruptedException {

        Call<PlantProfile> plantProfileCall = plantProfileApi.activatePlantProfile(24,8);
        plantProfileCall.enqueue(new Callback<PlantProfile>() {
            @Override
            public void onResponse(Call<PlantProfile> call, Response<PlantProfile> response) {
                if(response.isSuccessful())
                {
                    responseCode = response.code();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call<PlantProfile> call, Throwable t) {

            }
        });
        //latch.await();

        assertEquals("Response successful", 200, responseCode);
        assertNotEquals(0, responseCode);
    }


    @After
    public void clearResponseCode()
    {
        responseCode = 0;
    }










}
