package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.model.GreenHouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GreenHouseApi {

    String route = "GreenHouses";

    @GET(route + "/{uId}")
    Call<List<GreenHouse>> getGreenHouses(@Path("uId") long userId);

    @POST(route + "/{uId}")
    Call<GreenHouse> addGreenHouse(@Path("uId") long userId, @Body GreenHouse greenHouse);

    @PATCH(route)
    Call<GreenHouse> updateGreenHouse(@Body GreenHouse greenHouse);

    @DELETE(route + "/{gId}")
    Call<GreenHouse> deleteGreenHouse(@Path("gId") long greenHouseId);

}
