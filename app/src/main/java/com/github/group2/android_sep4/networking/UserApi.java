package com.github.group2.android_sep4.networking;

import static com.github.group2.android_sep4.repository.ServiceGenerator.API_KEY;

import com.github.group2.android_sep4.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    String route = "Users/";

    @Headers({API_KEY})
    @GET(route + "byUsername/{username}")
    Call<User> getUserByUsername(@Path("username") String username);

    @Headers({API_KEY})
    @GET(route + "byId/{id}")
    Call<User> getUserById(@Path("id") long id);

    @Headers({API_KEY})
    @GET(route + "byEmail/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @Headers({API_KEY})
    @POST(route + "add")
    Call<User> addUser(@Body User user);

    @Headers({API_KEY})
    @PATCH(route + "update")
    Call<User> updateUser(@Body User user);

    @Headers({API_KEY})
    @DELETE(route + "remove/{id}")
    Call<User> deleteUser(@Path("id") long id);

    @Headers({API_KEY})
    @GET(route + "login")
    Call<User> login(@Query("email") String email, @Query("password") String password);

    @Headers({API_KEY})
    Call<Void> registerNotificationClient(long userId, String tokenValue);
}
