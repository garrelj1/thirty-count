package com.garrell.co.thirtycount.clock;

import com.garrell.co.baseapp.common.observable.BaseObservable;
import com.techyourchance.threadposter.UiThreadPoster;

import java.util.Timer;
import java.util.TimerTask;

public class Clock extends BaseObservable<Clock.Listener> implements ClockTick.Listener {

    private final UiThreadPoster uiThreadPoster;

    public interface Listener {
        void onUpdateTime(int time);
        void onTimerReset();
    }

    private final int PERIOD_MILLI = 1000;
    private final int PERIOD_SEC = 1;
    private int totalTime = 30;
    private final Timer timer = new Timer();

    public Clock(UiThreadPoster uiThreadPoster) {
        this.uiThreadPoster = uiThreadPoster;
    }

    public void start() {
        timer.schedule(newTimerTask(), PERIOD_MILLI, PERIOD_MILLI);
    }

    public void stop() {
        timer.cancel();
    }

    @Override
    public void tick() {

        if (totalTime == 1) {
            totalTime = 30;
            notifyReset();
        }
        else
            totalTime -= PERIOD_SEC;

        notifyTick();
    }

    private void notifyReset() {
        uiThreadPoster.post(() -> {
            for (Listener l : getListeners())
                l.onTimerReset();
        });
    }

    private void notifyTick() {
        uiThreadPoster.post(() -> {
            for (Listener l : getListeners())
                l.onUpdateTime(totalTime);
        });
    }

    private TimerTask newTimerTask() {
        return new ClockTick(this);
    }

}
