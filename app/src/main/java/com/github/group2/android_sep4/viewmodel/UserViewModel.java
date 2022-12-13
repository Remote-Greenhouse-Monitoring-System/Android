package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.NotificationRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.NotificationRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

public class UserViewModel extends ViewModel {

    private UserRepository repository;
    private NotificationRepository notificationRepository;

    public UserViewModel() {
        repository = UserRepositoryImpl.getInstance();
        notificationRepository = NotificationRepositoryImpl.getInstance();
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
        repository.logout((userId)->{
            notificationRepository.unregisterNotificationToken((long) userId);
        });

    }

    public void deleteUser(long userId) {
        repository.deleteUser(userId);
    }

    public void updateUser(User user) {

        repository.updateUser(user);
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public LiveData<String> getSuccessMessage() {
        return repository.getSuccessMessage();
    }

    public void resetInfos() {
        repository.resetInfo();
    }


}
