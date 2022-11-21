package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.entity.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlantProfileApi {

    String route = "plantProfile/";

    @POST(route + "/{username}")
    Call<PlantProfile> createPlantProfile(@Path("username") String username, @Body PlantProfile plantProfile);

    @DELETE(route + "/{plantProfileId}")
    Call removePlantProfile(@Path("plantProfileId") long id);

    @PUT(route + "/{plantProfileId}")
    Call<PlantProfile> updatePlantProfile(@Path("plantProfileId") long id, @Body PlantProfile plantProfile);

    @GET(route + "/{pageNumber}/{pageSize}")
    Call<List<PlantProfile>> getPremadePlantProfiles(@Path("pageNumber") int pageNumber, @Path("pageSize") int pageSize);

    @GET(route + "/{plantProfileId")
    Call<PlantProfile> getPlantProfileById(@Path("plantProfileId") long id);

    @PUT(route + "/{plantProfileId")
    Call activatePlantProfile(@Path("plantProfileId") long id);

}
