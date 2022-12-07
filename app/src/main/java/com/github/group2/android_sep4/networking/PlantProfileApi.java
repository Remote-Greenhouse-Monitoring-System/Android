package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.entity.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlantProfileApi {

    String route = "PlantProfiles";

    @POST(route + "/add/{uId}")
    Call<PlantProfile> createPlantProfile(@Path("uId") int uId, @Body PlantProfile plantProfile);

    @DELETE(route + "/{pId}")
    Call removePlantProfile(@Path("pId") int id);

    @PATCH(route + "/update")
    Call<PlantProfile> updatePlantProfile(@Body PlantProfile plantProfile);

    @GET(route + "plantProfilesForUser/{uId}")
    Call<List<PlantProfile>> getPlantProfilesForUser(@Path("uId") int uId);

    @GET(route)
    Call<List<PlantProfile>> getPlantProfiles();

    @GET(route + "/PlantP/{pId}")
    Call<PlantProfile> getPlantProfileById(@Path("pId") int id);

    @PATCH(route + "/activate/{pId}/{gId}")
    Call activatePlantProfile(@Path("pId") int pId,@Path("gId") int gId);

    @GET(route + "/activated/{gId}")
    Call<List<PlantProfile>> getActivatedPlantProfiles(@Path("gId") int gId);


}
