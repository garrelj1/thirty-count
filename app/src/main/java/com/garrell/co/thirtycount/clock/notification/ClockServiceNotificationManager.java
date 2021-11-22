package com.garrell.co.thirtycount.clock.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.common.notification.NotificationHelper;

public class ClockServiceNotificationManager {

    static final String CHANNEL_ID = ClockServiceNotificationManager.class.getSimpleName();

    private final NotificationHelper   notificationHelper;
    private       Notification.Builder builder;

    public ClockServiceNotificationManager(NotificationHelper notificationHelper) {
        if (notificationHelper == null)
            throw new IllegalArgumentException("Cannot be null");

        this.notificationHelper = notificationHelper;
    }

    public void setNotificationTime(int time) {
        if (builder == null)
            return;

        builder.setContentText(Integer.toString(time));
        //notificationHelper.notify(0, builder.build());
    }

    public Notification setupForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createNotificationChannel();

        return createNotification();
    }

    private Notification createNotification() {
        if (builder == null) {
            builder = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                    notificationHelper.getNotificationBuilder(CHANNEL_ID) :
                    notificationHelper.getNotificationBuilder();

            builder.setContentTitle("Thirty Count")
                    .setContentText("30")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true);
        }

        return builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = getNotificationChannel();
        notificationHelper.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getNotificationChannel() {
        return new NotificationChannel(
                CHANNEL_ID,
                "Thirty count",
                NotificationManager.IMPORTANCE_HIGH
        );
    }
}
