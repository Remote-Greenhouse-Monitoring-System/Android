package com.github.group2.android_sep4.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.User;
import com.github.group2.android_sep4.repository.login.MockUserRepositoryImpl;
import com.github.group2.android_sep4.repository.login.UserRepository;

public class UserViewModel extends ViewModel {


    private UserRepository repository;


    public UserViewModel() {
        repository = MockUserRepositoryImpl.getInstance();
    }

    public void signUp(String username, String email, String password) {
        repository.addUser(username, email, password);
    }

    public LiveData<User> getCurrentUser(){
        return repository.getCurrentUser();
    }

    public LiveData<String> getError(){
        return repository.getError();
    }

}
