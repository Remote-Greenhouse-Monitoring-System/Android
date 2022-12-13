package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.Greenhouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GreenhouseApi {

    String route = "GreenHouses";

    @Headers({API_KEY})
    @GET(route + "/{uId}")
    Call<List<Greenhouse>> getGreenhouses(@Path("uId") long userId);

    @Headers({API_KEY})
    @POST(route + "/{uId}")
    Call<Greenhouse> addGreenhouse(@Path("uId") long userId, @Body Greenhouse greenhouse);

    @Headers({API_KEY})
    @PATCH(route)
    Call<Greenhouse> updateGreenhouse(@Body Greenhouse greenhouse);

    @Headers({API_KEY})
    @DELETE(route + "/{gId}")
    Call<Greenhouse> deleteGreenhouse(@Path("gId") long greenhouseId);

    @Headers({API_KEY})
    @GET(route + "/greenhousesWithLastMeasurements/{uId}")
    Call<List<Greenhouse>> getGreenHouseByUserWithLastMeasurement(@Path("uId") long userId);

}
