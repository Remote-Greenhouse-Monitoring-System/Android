package com.github.group2.android_sep4.repository;

public interface NotificationRepository {

    void registerNotificationToken(String token, long userId);
    void unregisterNotificationToken( long userId);

}
