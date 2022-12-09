package com.github.group2.android_sep4.networking;


import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.PlantProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlantProfileApi
{
    String route = "PlantProfiles";
    @Headers({API_KEY})
    @POST(route + "/add/{uId}")
    Call<PlantProfile> addPlantProfile(@Path("uId") long userId, @Body PlantProfile plantProfile);

    @Headers({API_KEY})
    @DELETE(route + "/remove/{pId}")
    Call<PlantProfile> deletePlantProfile(@Path("pId") long plantProfileId);

    @Headers({API_KEY})
    @PATCH(route + "/update")
    Call<PlantProfile> updatePlantProfile(@Body PlantProfile plantProfile);

    @Headers({API_KEY})
    @GET(route + "/plantProfilesForUser/{uId}")
    Call<List<PlantProfile>> getPlantProfilesForUser(@Path("uId") long userId);

    @Headers({API_KEY})
    @GET(route)
    Call<List<PlantProfile>> getPlantProfiles();

    @Headers({API_KEY})
    @GET(route + "/plantP/{pId}")
    Call<PlantProfile> getPlantProfile(@Path("pId") long plantProfileId);

    @Headers({API_KEY})
    @PATCH(route + "/activate/{pId}/{gId}")
    Call<Void> activatePlantProfile(@Path("pId") long plantProfileId, @Path("gId") long greenHouseId);

    @Headers({API_KEY})
    @GET(route + "/activated/{gId}")
    Call<PlantProfile> getActivatedPlantProfile(@Path("gId") long greenHouseId);

    @Headers({API_KEY})
    @PATCH(route + "/deactivate/{gId}")
    Call<Void> deactivatePlantProfile(@Path("gId") long greenHouseId);


}

