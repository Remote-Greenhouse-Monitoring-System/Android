package com.github.group2.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.group2.android_sep4.model.User;
import com.github.group2.android_sep4.repository.NotificationRepository;
import com.github.group2.android_sep4.repository.UserRepository;
import com.github.group2.android_sep4.repository.implementaion.NotificationRepositoryImpl;
import com.github.group2.android_sep4.repository.implementaion.UserRepositoryImpl;

public class MainActivityViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public MainActivityViewModel() {
        userRepository = UserRepositoryImpl.getInstance();
        notificationRepository = NotificationRepositoryImpl.getInstance();
    }

    public LiveData<User> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void init(User userToSave) {
        userRepository.init(userToSave);
    }

    public void registerToken(long id, String token) {
        notificationRepository.registerNotificationToken(token, id);
    }

    public void unregisterNotificationToken(User user) {
        notificationRepository.unregisterNotificationToken(user.getId());
    }
}
