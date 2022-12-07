package com.github.group2.android_sep4.repository;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.model.User;

public interface UserRepository {


    void addUser(String username, String email, String password);

    LiveData<User> getCurrentUser();

    LiveData<String> getError();

    void login(String email, String password);

    void init(User savedLoggedInUser);

    void logout();

}