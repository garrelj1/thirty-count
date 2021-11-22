package com.garrell.co.baseapp.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.garrell.co.baseapp.application.MyApplication;
import com.garrell.co.baseapp.common.dependencyinjection.ServiceCompositionRoot;

public class BaseService extends Service {

    protected enum ServiceState {
        RUNNING,
        STOPPED
    }

    protected ServiceState serviceState = ServiceState.STOPPED;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        final IBinder BINDING_NOT_SUPPORTED = null;
        return BINDING_NOT_SUPPORTED;
    }

    private ServiceCompositionRoot compositionRoot;

    public ServiceCompositionRoot getCompositionRoot() {
        if (compositionRoot == null) {
            compositionRoot = new ServiceCompositionRoot(
                    ((MyApplication) getApplication()).getApplicationCompositionRoot(),
                    this
            );
        }
        return compositionRoot;
    }

}
