package com.garrell.co.baseapp.screens.common.dialogs.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.mvcviews.BaseObservableViewMvc;

public class InfoViewMvcImpl extends BaseObservableViewMvc<InfoViewMvc.InfoViewMvcListener>
        implements InfoViewMvc {

    private final TextView mTxtTitle;
    private final TextView mTxtMessage;
    private final Button mBtnDismiss;

    public InfoViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_info_prompt, container, false));

        mTxtTitle = findViewById(R.id.txt_title);
        mTxtMessage = findViewById(R.id.txt_message);
        mBtnDismiss = findViewById(R.id.btn_positive);

        findViewById(R.id.btn_negative).setVisibility(View.GONE); // not used in info functionality

        mBtnDismiss.setOnClickListener(v -> {
            for (InfoViewMvcListener listener : getListeners()) {
                listener.onDismissClicked();
            }
        });
    }

    @Override
    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    @Override
    public void setMessage(String message) {
        mTxtMessage.setText(message);
    }

    @Override
    public void setDismissCaption(String dismissCaption) {
        mBtnDismiss.setText(dismissCaption);
    }

}
