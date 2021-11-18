package com.garrell.co.thirtycount.screens.countdown.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.ViewMvcFactory;
import com.garrell.co.baseapp.screens.common.controller.BaseFragment;
import com.garrell.co.thirtycount.clock.Clock;
import com.garrell.co.thirtycount.clock.reset.PlayResetToneUseCase;
import com.garrell.co.thirtycount.screens.countdown.view.CountdownViewMvc;

public class CountdownFragment extends BaseFragment implements Clock.Listener {

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    private Clock clock;
    private ViewMvcFactory viewMvcFactory;
    private PlayResetToneUseCase playToneUseCase;
    private CountdownViewMvc viewMvc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewMvcFactory = getControllerCompositionRoot().getViewMvcFactory();
        clock = getControllerCompositionRoot().getClock();
        playToneUseCase = getControllerCompositionRoot().getPlayResetToneUseCase();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewMvc = viewMvcFactory.newCountdownViewMvc();
        return viewMvc.getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        clock.registerListener(this);

        clock.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        clock.unregisterListener(this);

        clock.stop();
    }

    @Override
    public void onUpdateTime(int time) {
        viewMvc.setTime(time);
    }

    @Override
    public void onTimerReset() {
        playToneUseCase.playTone();
    }

}
