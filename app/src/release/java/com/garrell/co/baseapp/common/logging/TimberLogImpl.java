package com.garrell.co.baseapp.common.logging;

import timber.log.Timber;

public class TimberLogImpl implements TimberLog {

    public static void init() {
        Timber.plant(new ReleaseTree());
    }

}
