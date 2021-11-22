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

    private final int TICK_LENGTH_SECOND = 1;
    private int       totalTime          = 30;
    private final Timer timer = new Timer();

    public Clock(UiThreadPoster uiThreadPoster) {
        this.uiThreadPoster = uiThreadPoster;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void start() {
        int TICK_LENGTH_MILLI = TICK_LENGTH_SECOND * 1000;
        timer.schedule(newTimerTask(), TICK_LENGTH_MILLI, TICK_LENGTH_MILLI);
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
            totalTime -= TICK_LENGTH_SECOND;

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
