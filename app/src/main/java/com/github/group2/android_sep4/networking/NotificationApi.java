package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.Greenhouse;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationApi {

    String route = "Notifications";

    @Headers({API_KEY})
    @POST(route + "/register/{token}/{uId}")
    Call<Void> registerNotificationService(@Path("token") String token, @Path("uId") long userId);

    @Headers({API_KEY})
    @PATCH( route + "/unregister/{uId}")
    Call<Void> unregisterNotificationService( @Path("uId") long userId);
}
