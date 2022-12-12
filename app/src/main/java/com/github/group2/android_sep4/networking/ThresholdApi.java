package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.Threshold;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ThresholdApi {

    String route = "Thresholds";

    @Headers({API_KEY})
    @GET(route + "/get/{pId}")
    Call<Threshold> getThreshold(@Path("pId") long plantProfileId);

    @Headers({API_KEY})
    @PATCH(route + "/update/{pId}")
    Call<Void> updateThreshold(@Path("pId") long plantProfileId, @Body Threshold threshold);
}
