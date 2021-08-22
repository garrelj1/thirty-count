package com.garrell.co.baseapp.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.garrell.co.baseapp.R;
import com.garrell.co.baseapp.screens.common.ViewMvcFactory;
import com.garrell.co.baseapp.screens.common.dialogs.DialogsManager;
import com.garrell.co.baseapp.screens.common.screennavigator.ScreensNavigator;
import com.ncapdevi.fragnav.FragNavController;
import com.garrell.co.baseapp.common.permissions.PermissionsHelper;
import com.techyourchance.dialoghelper.DialogHelper;

public class ActivityCompositionRoot extends ComponentCompositionRoot {

    private final AppCompatActivity activity;

    public ActivityCompositionRoot(ApplicationCompositionRoot applicationCompositionRoot,
                                   AppCompatActivity activity) {
        super(applicationCompositionRoot);
        this.activity = activity;
    }

    public Context getContext() {
        return activity;
    }

    private LayoutInflater getLayoutInflater() {
        return activity.getLayoutInflater();
    }


    private FragmentManager getFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    public ScreensNavigator getScreenNavigator() {
        return new ScreensNavigator(activity, getFragNavController());
    }

    private FragNavController getFragNavController() {
        return new FragNavController(getFragmentManager(), R.id.fragment_container_view);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(getDialogHelper());
    }

    private DialogHelper getDialogHelper() {
        return new DialogHelper(getFragmentManager());
    }

    public PermissionsHelper getPermissionsHelper() {
        return new PermissionsHelper(activity);
    }
}
