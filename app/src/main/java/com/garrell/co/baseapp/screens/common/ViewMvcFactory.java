package com.garrell.co.baseapp.screens.common;

import android.view.LayoutInflater;

import com.garrell.co.baseapp.screens.common.dialogs.info.InfoViewMvc;
import com.garrell.co.baseapp.screens.common.dialogs.info.InfoViewMvcImpl;
import com.garrell.co.baseapp.screens.common.dialogs.prompt.PromptViewMvc;
import com.garrell.co.baseapp.screens.common.dialogs.prompt.PromptViewMvcImpl;

public class ViewMvcFactory {

    private final LayoutInflater layoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public InfoViewMvc newInfoViewMvc() {
        return new InfoViewMvcImpl(layoutInflater, null);
    }

    public PromptViewMvc newPromptViewMvc() {
        return new PromptViewMvcImpl(layoutInflater, null);
    }

}
