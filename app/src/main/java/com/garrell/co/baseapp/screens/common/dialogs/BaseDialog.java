package com.garrell.co.baseapp.screens.common.dialogs;

import androidx.annotation.UiThread;
import androidx.fragment.app.DialogFragment;

import com.garrell.co.baseapp.screens.common.controller.BaseActivity;
import com.garrell.co.baseapp.common.dependencyinjection.ActivityCompositionRoot;

import org.jetbrains.annotations.Nullable;

/**
 * Base class for all dialogs
 */
public abstract class BaseDialog extends DialogFragment {

    private ActivityCompositionRoot mControllerComponent;

    @UiThread
    protected ActivityCompositionRoot getControllerCompositionRoot() {
        if (mControllerComponent == null) {
            mControllerComponent = ((BaseActivity) getActivity()).getCompositionRoot();
        }
        return mControllerComponent;
    }

    /**
     * Get this dialog's ID that was supplied with a call to one of {@link DialogsManager}'s
     * methods.
     * @return dialog's ID, or null if none was set
     */
    protected @Nullable String getDialogId() {
        return getControllerCompositionRoot().getDialogsManager().getDialogId(this);
    }

}
