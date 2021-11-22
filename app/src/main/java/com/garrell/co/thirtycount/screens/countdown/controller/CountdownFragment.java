package com.garrell.co.thirtycount.screens.countdown.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.common.eventbus.EventBusSubscriber;
import com.garrell.co.baseapp.screens.common.ViewMvcFactory;
import com.garrell.co.baseapp.screens.common.controller.BaseFragment;
import com.garrell.co.thirtycount.clock.Clock;
import com.garrell.co.thirtycount.clock.ClockService;
import com.garrell.co.thirtycount.clock.event.TimeUpdatedEvent;
import com.garrell.co.thirtycount.clock.event.TimerResetEvent;
import com.garrell.co.thirtycount.clock.reset.PlayResetToneUseCase;
import com.garrell.co.thirtycount.screens.countdown.view.CountdownViewMvc;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CountdownFragment extends BaseFragment {

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    private ViewMvcFactory viewMvcFactory;


    private EventBusSubscriber eventBusSubscriber;
    private CountdownViewMvc viewMvc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewMvcFactory = getControllerCompositionRoot().getViewMvcFactory();
        eventBusSubscriber = getControllerCompositionRoot().getEventBusSubscriber();


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
        eventBusSubscriber.register(this);

        ClockService.launchClockService(getControllerCompositionRoot().getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBusSubscriber.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTimeUpdateEvent(TimeUpdatedEvent timeUpdatedEvent) {
        viewMvc.setTime(timeUpdatedEvent.time);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTimerResetEvent(TimerResetEvent timerResetEvent) {
    }

}
