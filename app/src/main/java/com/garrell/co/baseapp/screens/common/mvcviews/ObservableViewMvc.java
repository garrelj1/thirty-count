package com.garrell.co.baseapp.screens.common.mvcviews;

public interface ObservableViewMvc<LISTENER_CLASS> extends ViewMvc {

    void registerListener(LISTENER_CLASS listener);

    void unregisterListener(LISTENER_CLASS listener);
}
