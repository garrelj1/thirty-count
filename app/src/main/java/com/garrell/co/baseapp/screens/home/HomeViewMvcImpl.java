package com.garrell.co.baseapp.screens.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.mvcviews.BaseObservableViewMvc;

public class HomeViewMvcImpl extends BaseObservableViewMvc<HomeViewMvc.Listener>
        implements HomeViewMvc {

    private final Button mBtnRequestPermissions;

    public HomeViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_home, container, false));

        mBtnRequestPermissions = findViewById(R.id.btn_request_permissions);

        mBtnRequestPermissions.setOnClickListener(v -> {
            for (Listener listener : getListeners()) {
                listener.onRequestPermissionsClicked();
            }
        });
    }

    @Override
    public void enableRequestPermissionsButton() {
        mBtnRequestPermissions.setEnabled(true);
    }

    @Override
    public void disableRequestPermissionsButton() {
        mBtnRequestPermissions.setEnabled(false);
    }
}
