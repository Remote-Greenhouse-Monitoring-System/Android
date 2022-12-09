package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.networking.UserApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.UserRepository;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepository instance;
    private static Lock lock = new ReentrantLock();

    private MutableLiveData<String> errorMessage, successMessage;
    private MutableLiveData<User> currentUser;
    private UserApi userApi;

    private UserRepositoryImpl() {
        errorMessage = new MutableLiveData<>();
        currentUser = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
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
                    setErrorMessage(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    private void setErrorMessage(Response response) {
        String errorMessage = null;
        try {
            errorMessage = "Error :"+ response.code()+ " " +
                    response.errorBody().string();
        } catch (IOException e) {
            this.errorMessage.setValue("Cannot connect to server");
        }
        this.errorMessage.setValue(errorMessage);
    }

    @Override
    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    @Override
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void updateUser(User user) {
        Call<User> call = userApi.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser.setValue(response.body());
                    successMessage.setValue("User updated successfully");

                } else {
                    setErrorMessage(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });
    }

    @Override
    public void deleteUser(long id) {
        Call<Void> call = userApi.deleteUser(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    successMessage.setValue("User deleted successfully");

                    currentUser.setValue(null);
                } else {
                    System.out.println("\nOn else ");
                    setErrorMessage(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
            }
        });
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
                    setErrorMessage(response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorMessage.setValue("Cannot connect to server");
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

    @Override
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }
}
