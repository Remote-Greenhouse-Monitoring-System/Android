package com.github.group2.android_sep4.repository.implementaion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.networking.UserApi;
import com.github.group2.android_sep4.repository.ServiceGenerator;
import com.github.group2.android_sep4.repository.UserRepository;

import com.github.group2.android_sep4.repository.callback.ApiCallback;
import com.google.firebase.messaging.FirebaseMessaging;

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

    private FirebaseMessaging firebaseMessaging;
    private MutableLiveData<String> token;

    private UserRepositoryImpl() {
        errorMessage = new MutableLiveData<>();
        currentUser = new MutableLiveData<>();
        successMessage = new MutableLiveData<>();
        userApi = ServiceGenerator.getUserApi();
        token = new MutableLiveData<>();
        firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                errorMessage.postValue("Fetching FCM registration token failed");
                return;
            }
            token.setValue(task.getResult());
        });
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
        resetInfo();
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
        String errorMessage;
        try {
            errorMessage = "Error :" + response.code() + " " +
                    response.errorBody().string();
            this.errorMessage.setValue(errorMessage);
        } catch (IOException e) {
            this.errorMessage.setValue("Cannot connect to server");
        }
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
        resetInfo();
        Call<User> call = userApi.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userFromServer = response.body();
                    currentUser.setValue(userFromServer);
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
        resetInfo();
        Call<User> call = userApi.deleteUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser.setValue(null);
                    successMessage.setValue(response.body().getUsername() + " has been deleted");
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
    public void login(String email, String password) {
        resetInfo();
        Call<User> call = userApi.getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User userFromServerWithHashedPass = response.body();
                    if (User.checkPassword(password, userFromServerWithHashedPass.getPassword())) {
                        currentUser.setValue(userFromServerWithHashedPass);
                    } else {
                        errorMessage.setValue("Incorrect password, please try again");
                    }
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
    public void logout(ApiCallback callback) {
        resetInfo();
        long id = currentUser.getValue().getId();
        callback.onResponse(id);
        currentUser.setValue(null);
    }

    @Override
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    @Override
    public void resetInfo() {
        errorMessage.setValue(null);
        successMessage.setValue(null);
    }
}