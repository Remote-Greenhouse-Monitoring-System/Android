package com.github.group2.android_sep4.repository.implementation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.networking.UserApi;
import com.github.group2.android_sep4.repository.UserRepository;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepository instance;
    private static Lock lock = new ReentrantLock();

    private MutableLiveData<String> error;
    private MutableLiveData<User> currentUser;
    private UserApi userApi;



    private UserRepositoryImpl() {
        error = new MutableLiveData<>();
        currentUser = new MutableLiveData<>();
        userApi = ServiceGenerator.getUserApi();


    }


    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void addUser(String username, String email, String password) {
        Call<User> call = userApi.addUser(new User(email, username, password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                error.setValue("Cannot connect to server");
            }
        });
    }

    private void setError(Response<User> response) {
        String errorMessage = "Error :"+ response.code()+ " " +
                response.message();
        error.setValue(errorMessage);


    }

    @Override
    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    @Override
    public LiveData<String> getError() {
        return error;
    }

    @Override
    public void login(String email, String password) {

        Call<User> call = userApi.login(email, password);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser.setValue(response.body());
                } else {
                    setError(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                error.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public void init(User savedLoggedInUser) {
        currentUser.setValue(savedLoggedInUser);
    }

    @Override
    public void logout() {
        currentUser.setValue(null);
    }
}
