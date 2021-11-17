package com.garrell.co.thirtycount.clock;

import java.util.TimerTask;

public class ClockTick extends TimerTask {

    interface Listener {
        void tick();
    }

    private Listener listener;

    public ClockTick(Listener listener) {
        if (listener == null)
            throw new IllegalStateException("Must not pass null for listener");

        this.listener = listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.tick();
    }
}
