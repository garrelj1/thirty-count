package com.garrell.co.baseapp.screens.common.controller;

public interface BackPressDispatcher {
    void registerListener(BackPressListener listener);
    void unregisterListener(BackPressListener listener);
}
