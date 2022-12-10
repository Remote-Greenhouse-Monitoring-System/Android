package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.GreenHouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GreenHouseApi {

    String route = "GreenHouses";
    String apiKey = "JYP!$jFqqFxmy@TsF6zBNMaSd3Fd&";

    @Headers({API_KEY})
    @GET(route + "/{uId}")
    Call<List<GreenHouse>> getGreenHouses(@Path("uId") long userId);

    @Headers({API_KEY})
    @POST(route + "/{uId}")
    Call<GreenHouse> addGreenHouse(@Path("uId") long userId, @Body GreenHouse greenHouse);

    @Headers({API_KEY})
    @PATCH(route)
    Call<GreenHouse> updateGreenHouse(@Body GreenHouse greenHouse);

    @Headers({API_KEY})
    @DELETE(route + "/{gId}")
    Call<GreenHouse> deleteGreenHouse(@Path("gId") long greenHouseId);

    @Headers({API_KEY})
    @GET(route + "/greenhousesWithLastMeasurements/{uId}")
    Call<List<GreenHouse>> getGreenHouseByUserWithLastMeasurement(@Path("uId") long userId);

}
