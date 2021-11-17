package com.garrell.co.baseapp.application;

import android.app.Application;

import androidx.annotation.UiThread;

import com.garrell.co.baseapp.common.dependencyinjection.ApplicationCompositionRoot;
import com.garrell.co.baseapp.common.logging.TimberLogImpl;

public class MyApplication extends Application {

    private ApplicationCompositionRoot applicationCompositionRoot;

    @UiThread
    public ApplicationCompositionRoot getApplicationCompositionRoot() {
        if (applicationCompositionRoot == null) {
            applicationCompositionRoot = new ApplicationCompositionRoot(this);
        }
        return applicationCompositionRoot;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimberLogImpl.init();
    }

}
