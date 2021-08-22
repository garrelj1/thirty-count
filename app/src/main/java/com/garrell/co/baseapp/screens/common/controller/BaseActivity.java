package com.garrell.co.baseapp.screens.common.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.garrell.co.baseapp.application.MyApplication;
import com.garrell.co.baseapp.common.dependencyinjection.ActivityCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ActivityCompositionRoot compositionRoot;

    public ActivityCompositionRoot getCompositionRoot() {
        if (compositionRoot == null) {
            compositionRoot = new ActivityCompositionRoot(
                    ((MyApplication) getApplication()).getApplicationCompositionRoot(), this);
        }
        return compositionRoot;
    }

}
