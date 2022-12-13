package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

public class SingUpViewModel extends ViewModel {
    private final UserRepository repository = UserRepositoryImpl.getInstance();

    public LiveData<User> getCurrentUser() {
        return repository.getCurrentUser();
    }

    public LiveData<String> getError() {
        return repository.getErrorMessage();
    }

    public void signUp(String username, String email, String password) {
        repository.addUser(username, email, password);
    }

    public void resetInfos() {

        repository.resetInfos();
    }
}
