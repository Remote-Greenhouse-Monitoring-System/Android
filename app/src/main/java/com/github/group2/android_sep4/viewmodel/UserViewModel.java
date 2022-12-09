package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

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
        return repository.getErrorMessage();
    }

    public void login(String email, String password) {
        repository.login(email, password);

    }

    public void logout() {

        repository.logout();
    }

    public void deleteUser(long userId) {
        repository.deleteUser(userId);
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return repository.getSuccessMessage();
    }
}
