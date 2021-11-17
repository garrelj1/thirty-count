package com.garrell.co.baseapp.screens.common.dialogs.prompt;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.mvcviews.BaseObservableViewMvc;

public class PromptViewMvcImpl extends BaseObservableViewMvc<PromptViewMvc.PromptViewMvcListener>
        implements PromptViewMvc {

    private final TextView mTxtTitle;
    private final TextView mTxtMessage;
    private final Button mBtnPositive;
    private final Button mBtnNegative;

    public PromptViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_info_prompt, container, false));

        mTxtTitle = findViewById(R.id.txt_title);
        mTxtMessage = findViewById(R.id.txt_message);
        mBtnPositive = findViewById(R.id.btn_positive);
        mBtnNegative = findViewById(R.id.btn_negative);

        mBtnPositive.setOnClickListener(v -> {
            for (PromptViewMvcListener listener : getListeners()) {
                listener.onPositiveButtonClicked();
            }
        });

        mBtnNegative.setOnClickListener(v -> {
            for (PromptViewMvcListener listener : getListeners()) {
                listener.onNegativeButtonClicked();
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
    public void setPositiveButtonCaption(String positiveButtonCaption) {
        mBtnPositive.setText(positiveButtonCaption);
    }

    @Override
    public void setNegativeButtonCaption(String negativeButtonCaption) {
        mBtnNegative.setText(negativeButtonCaption);
    }
}
