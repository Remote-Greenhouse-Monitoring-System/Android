package com.github.group2.android_sep4.repository.login;

import androidx.lifecycle.LiveData;

import com.github.group2.android_sep4.entity.User;

public interface UserRepository {


    void addUser(String username, String email, String password);

    LiveData<User> getCurrentUser();

    LiveData<String> getError();

    void login(String email, String password);
}
