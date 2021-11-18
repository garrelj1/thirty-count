package com.garrell.co.thirtycount.screens.countdown.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.common.observable.BaseObservable;
import com.garrell.co.baseapp.screens.common.mvcviews.BaseObservableViewMvc;
import com.garrell.co.baseapp.screens.common.mvcviews.BaseViewMvc;

public class CountdownViewMvcImpl extends BaseViewMvc implements CountdownViewMvc {

    private TextView clock;

    public CountdownViewMvcImpl(LayoutInflater layoutInflater, ViewGroup container) {
        setRootView(layoutInflater.inflate(R.layout.layout_home, container, false));
        clock = findViewById(R.id.clock);
    }

    @Override
    public void setTime(int time) {
        clock.setText(Integer.toString(time));
    }

}
