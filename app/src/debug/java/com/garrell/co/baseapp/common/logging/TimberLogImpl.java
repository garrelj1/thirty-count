package com.garrell.co.baseapp.common.logging;

import timber.log.Timber;

public class TimberLogImpl implements TimberLog {

    public static void init() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return String.format("C:%s:%s",
                        super.createStackElementTag(element),
                        element.getLineNumber());
            }
        });
    }

}
