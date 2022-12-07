package com.github.group2.android_sep4.repository.implementation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MockUserRepositoryImpl implements UserRepository {

    private static UserRepository instance;
    private static Lock lock = new ReentrantLock();

    private MutableLiveData<String> error;
    private List<User> allUsersMock;
    private  MutableLiveData<User> currentUser;


    private MockUserRepositoryImpl(){
        allUsersMock = new ArrayList<>();
        error = new MutableLiveData<>();
        currentUser = new MutableLiveData<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new MockUserRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void addUser(String username, String email, String password) {
        for (User user:allUsersMock) {
            if (user.getEmail().equalsIgnoreCase(email)){
                error.setValue("Account with this email address already exists");
                return;
            }
        }
        User userToAdd = new User(email, username, password);
        allUsersMock.add(userToAdd);
        currentUser.setValue(userToAdd);

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

        User user = new User(email, password);
        for (User userInDb : allUsersMock) {
            if (user.equals(userInDb)) {
                currentUser.setValue(userInDb);
                return;
            }
            if (user.getEmail().equals(userInDb.getEmail())) {
                error.setValue("Incorrect password");
                return;
            }
        }
        error.setValue("No user found with the provided email address");
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
