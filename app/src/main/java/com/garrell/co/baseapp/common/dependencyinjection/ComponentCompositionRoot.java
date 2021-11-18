package com.garrell.co.baseapp.common.dependencyinjection;

import android.content.Context;
import android.media.MediaPlayer;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.common.eventbus.EventBusPoster;
import com.garrell.co.baseapp.common.eventbus.EventBusSubscriber;
import com.garrell.co.thirtycount.clock.Clock;
import com.garrell.co.thirtycount.clock.reset.PlayResetToneUseCase;
import com.techyourchance.threadposter.BackgroundThreadPoster;
import com.techyourchance.threadposter.UiThreadPoster;

// Base class for object graph composition within the four Android App Components
// 1. Activities
// 2. Services
// 3. Broadcast receivers
// 4. Content providers

// NOTE: This class should only be extended by the Composition root of the Android App Components listed above
public abstract class ComponentCompositionRoot {

    private final ApplicationCompositionRoot applicationCompositionRoot;

    public ComponentCompositionRoot(ApplicationCompositionRoot applicationCompositionRoot) {
        this.applicationCompositionRoot = applicationCompositionRoot;
    }

    public Context getApplicationContext() {
        return applicationCompositionRoot.getContext();
    }

    public UiThreadPoster getUiThreadPoster() {
        return applicationCompositionRoot.getUiThreadPoster();
    }

    public BackgroundThreadPoster getBackgroundThreadPoster() {
        return applicationCompositionRoot.getBackgroundThreadPoster();
    }

    public EventBusPoster getEventBusPoster() {
        return applicationCompositionRoot.getEventBusPoster();
    }

    public EventBusSubscriber getEventBusSubscriber() {
        return applicationCompositionRoot.getEventBusSubscriber();
    }

    public Clock getClock() {
        return new Clock(getUiThreadPoster());
    }

}
