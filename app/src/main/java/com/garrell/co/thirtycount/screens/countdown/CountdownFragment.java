package com.garrell.co.thirtycount.screens.countdown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.controller.BaseFragment;
import com.garrell.co.thirtycount.clock.Clock;

public class CountdownFragment extends BaseFragment implements Clock.Listener {

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    private Clock clock;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        clock = getControllerCompositionRoot().getClock();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home, container, false);
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
        TextView tv = getView().findViewById(R.id.clock);
        tv.setText(Integer.toString(time));
    }

    @Override
    public void onTimerReset() {
    }

}
