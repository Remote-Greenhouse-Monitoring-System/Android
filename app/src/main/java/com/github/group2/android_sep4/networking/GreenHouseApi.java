package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.entity.GreenHouse;

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
    String key="ApiKey: JYP!$jFqqFxmy@TsF6zBNMaSd3Fd&";

    @Headers({key})
    @GET(route + "/{uId}")
    Call<List<GreenHouse>> getGreenHouses(@Path("uId") long userId);

    @Headers({key})
    @POST(route + "/{uId}")
    Call<GreenHouse> addGreenHouse(@Path("uId") long userId, @Body GreenHouse greenHouse);

    @Headers({key})    @PATCH(route)
    Call<GreenHouse> updateGreenHouse(@Body GreenHouse greenHouse);

    @Headers({key})    @DELETE(route + "/{gId}")
    Call<GreenHouse> deleteGreenHouse(@Path("gId") long greenHouseId);

}
