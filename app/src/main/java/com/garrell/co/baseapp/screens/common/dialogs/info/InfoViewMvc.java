package com.garrell.co.baseapp.screens.common.dialogs.info;

import com.garrell.co.baseapp.screens.common.mvcviews.ObservableViewMvc;

public interface InfoViewMvc extends ObservableViewMvc<InfoViewMvc.InfoViewMvcListener> {

    public interface InfoViewMvcListener {
        void onDismissClicked();
    }

    void setTitle(String title);
    void setMessage(String message);
    void setDismissCaption(String dismissCaption);

}
