package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.model.Threshold;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ThresholdApi
{
    String route = "Thresholds";

    @GET(route + "/get/{pId}")
    Call<Threshold> getThreshold(@Path("pId") long plantProfileId);

    @PATCH(route + "/update/{pId}")
    Call<Threshold> updateThreshold(@Path("pId") long plantProfileId, @Body Threshold threshold);
}
