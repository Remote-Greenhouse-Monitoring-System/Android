package com.github.group2.android_sep4.repository.implementaion;

import android.util.Log;

import com.github.group2.android_sep4.model.GreenHouse;
import com.github.group2.android_sep4.networking.NotificationApi;
import com.github.group2.android_sep4.repository.NotificationRepository;
import com.github.group2.android_sep4.repository.ServiceGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import retrofit2.Call;

public class NotificationRepositoryImpl implements NotificationRepository {

    private static NotificationRepository instance;
    private static Lock lock = new ReentrantLock();

    private NotificationApi api;

    private NotificationRepositoryImpl() {
        api = ServiceGenerator.getNotificationApi();
    }

    public static NotificationRepository getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new NotificationRepositoryImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public void registerNotificationToken(String token, long userId) {
        Call<GreenHouse> call = api.registerNotificationService(token, userId);
        call.enqueue(new retrofit2.Callback<GreenHouse>() {
            @Override
            public void onResponse(Call<GreenHouse> call, retrofit2.Response<GreenHouse> response) {
                if (response.isSuccessful()) {
                    Log.d("token", "Notification token registered successfully");
                } else {
                    Log.d("token", "Notification token registration failed");
                }
            }

            @Override
            public void onFailure(Call<GreenHouse> call, Throwable t) {
                System.out.println("Notification token not registered");
            }
        });

    }
}

