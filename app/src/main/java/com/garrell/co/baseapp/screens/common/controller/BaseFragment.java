package com.garrell.co.baseapp.screens.common.controller;

import androidx.fragment.app.Fragment;

import com.garrell.co.baseapp.common.dependencyinjection.ActivityCompositionRoot;

public class BaseFragment extends Fragment {

    private ActivityCompositionRoot controllerCompositionRoot;

    public ActivityCompositionRoot getControllerCompositionRoot() {
        if (controllerCompositionRoot == null) {
            controllerCompositionRoot = ((BaseActivity) getActivity()).getCompositionRoot();
        }
        return controllerCompositionRoot;
    }

}
