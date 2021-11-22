package com.garrell.co.thirtycount.clock.notification;

import android.app.Notification;
import android.os.Build;

import com.garrell.co.baseapp.common.notification.NotificationHelper;

public class ClockServiceNotificationManager {

    static final String CHANNEL_ID = ClockServiceNotificationManager.class.getSimpleName();

    private final NotificationHelper notificationHelper;

    public ClockServiceNotificationManager(NotificationHelper notificationHelper) {
        if (notificationHelper == null)
            throw new IllegalArgumentException("Cannot be null");

        this.notificationHelper = notificationHelper;
    }

    public Notification setupForegroundServiceNotification() {
        Notification.Builder builder = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                notificationHelper.getNotificationBuilder(CHANNEL_ID) :
                notificationHelper.getNotificationBuilder();

        return builder.build();
    }

}
