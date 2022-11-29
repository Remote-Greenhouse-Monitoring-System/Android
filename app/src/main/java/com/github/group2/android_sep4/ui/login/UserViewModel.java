package com.github.group2.android_sep4.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.entity.User;
import com.github.group2.android_sep4.repository.login.UserRepository;
import com.github.group2.android_sep4.repository.login.UserRepositoryImpl;

public class UserViewModel extends ViewModel {


    private UserRepository repository;


    public UserViewModel() {
//        repository = MockUserRepositoryImpl.getInstance();
        repository = UserRepositoryImpl.getInstance();
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

    public void login(String email, String password) {
        repository.login(email, password);

    }

    public void logout() {

        repository.logout();
    }
}
