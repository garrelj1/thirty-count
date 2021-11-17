package com.garrell.co.baseapp.screens.home;

import com.garrell.co.baseapp.screens.common.mvcviews.ObservableViewMvc;

public interface HomeViewMvc extends ObservableViewMvc<HomeViewMvc.Listener> {

    interface Listener {
        void onRequestPermissionsClicked();
    }

    void enableRequestPermissionsButton();
    void disableRequestPermissionsButton();

}
