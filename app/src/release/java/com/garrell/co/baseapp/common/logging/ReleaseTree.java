package com.garrell.co.baseapp.common.logging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {

    @Override
    protected void log(int priority,
                       @Nullable String tag,
                       @NonNull String message,
                       @Nullable Throwable t) {
        // TODO: Add crash reporting for release version
    }

}
