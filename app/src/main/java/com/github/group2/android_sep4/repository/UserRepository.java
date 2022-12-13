package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.callback.ApiCallback;

public interface UserRepository {

    void addUser(String username, String email, String password);

    LiveData<User> getCurrentUser();

    LiveData<String> getErrorMessage();

    void updateUser(User user);

    void deleteUser(long id);

    void login(String email, String password);

    void init(User savedLoggedInUser);

    void logout(ApiCallback callback);

    LiveData<String> getSuccessMessage();

    void resetInfo();
}
