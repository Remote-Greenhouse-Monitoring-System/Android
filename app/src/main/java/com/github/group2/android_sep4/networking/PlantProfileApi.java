package com.github.group2.android_sep4.networking;


import com.github.group2.android_sep4.model.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlantProfileApi
{
    String route = "PlantProfiles";

    @POST(route + "/add/{uId}")
    Call<PlantProfile> addPlantProfile(@Path("uId") long userId, @Body PlantProfile plantProfile);

    @DELETE(route + "/remove/{pId}")
    Call<PlantProfile> deletePlantProfile(@Path("pId") long plantProfileId);

    @PATCH(route + "/update")
    Call<PlantProfile> updatePlantProfile(@Body PlantProfile plantProfile);

    @GET(route + "/plantProfilesForUser/{uId}")
    Call<List<PlantProfile>> getPlantProfilesForUser(@Path("pId") long userId);


    @GET(route)
    Call<List<PlantProfile>> getPlantProfiles();

    @GET(route + "/plantP/{pId}")
    Call<PlantProfile> getPlantProfile(@Path("pId") long plantProfileId);

    @PATCH(route + "activate/{pId}/{gId}")
    Call<PlantProfile> activatePlantProfile(@Path("pId") long plantProfileId, @Path("gId") long greenHouseId);

    @GET(route + "/activated/{gId}")
    Call<PlantProfile> getActivatedPlantProfile(@Path("gId") long greenHouseId);



}

