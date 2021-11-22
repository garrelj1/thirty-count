package com.garrell.co.baseapp.common.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationHelper {

    private final Context context;
    private final NotificationManager notificationManager;

    public NotificationHelper(Context context) {
        this.notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(NotificationChannel notificationChannel) {
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotificationBuilder(String channelId) {
        return new Notification.Builder(context, channelId);
    }

    public Notification.Builder getNotificationBuilder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            throw new RuntimeException("This method was deprecated in Android O, use" +
                                               "a channel id when building a notification");
        return new Notification.Builder(context);
    }

}
