package com.github.group2.android_sep4.networking;

import com.github.group2.android_sep4.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    String route = "Users/";

    @GET(route + "byUsername/{username}")
    Call<User> getUserByUsername(@Path("username") String username);

    @GET(route + "byId/{id}")
    Call<User> getUserById(@Path("id") long id);

    @GET(route + "byEmail/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @POST(route + "add")
    Call<User> addUser( @Body User user);

    @PATCH(route + "update")
    Call<User> updateUser(@Body User user);

    @DELETE(route + "delete/{id}")
    Call<User> deleteUser(@Path("id") long id);


    @GET(route + "login")
    Call<User> login(@Query("email") String email, @Query("password") String password);




}
