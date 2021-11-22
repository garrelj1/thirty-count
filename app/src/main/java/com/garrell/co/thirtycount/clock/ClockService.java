package com.garrell.co.thirtycount.clock;

import android.content.Context;
import android.content.Intent;

import com.garrell.co.baseapp.common.eventbus.EventBusPoster;
import com.garrell.co.baseapp.common.service.BaseService;
import com.garrell.co.thirtycount.clock.event.TimeUpdatedEvent;
import com.garrell.co.thirtycount.clock.reset.PlayResetToneUseCase;

public class ClockService extends BaseService implements Clock.Listener {

    public static void launchClockService(Context appContext) {
        Intent intent = new Intent(appContext, ClockService.class);
        appContext.startService(intent);
    }

    private PlayResetToneUseCase playToneUseCase;

    private Clock                clock;
    private EventBusPoster eventBusPoster;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (serviceState == ServiceState.RUNNING)
            return super.onStartCommand(intent, flags, startId);

        //getCompositionRoot().getClockServiceNotificationManager();
        eventBusPoster = getCompositionRoot().getEventBusPoster();

        playToneUseCase = getCompositionRoot().getPlayResetToneUseCase();

        clock = getCompositionRoot().getClock();
        clock.registerListener(this);
        clock.start();

        serviceState = ServiceState.RUNNING;

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        clock.unregisterListener(this);
        clock.stop();

        serviceState = ServiceState.STOPPED;
    }

    @Override
    public void onUpdateTime(int time) {
        eventBusPoster.post(new TimeUpdatedEvent(time));
    }

    @Override
    public void onTimerReset() {
        playToneUseCase.playTone();
    }

}
