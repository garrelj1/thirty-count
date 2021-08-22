package com.garrell.co.baseapp.screens.common.dialogs.info;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.garrell.co.baseapp.common.eventbus.EventBusPoster;
import com.garrell.co.baseapp.screens.common.dialogs.BaseDialog;

/**
 * A dialog that can show title and message and has a single button. User's actions performed
 * in this dialog will be posted to event bus as {@link InfoDialogDismissedEvent}.
 */
public class InfoDialog extends BaseDialog implements InfoViewMvc.InfoViewMvcListener {

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_BUTTON_CAPTION = "ARG_POSITIVE_BUTTON_CAPTION";

    public static InfoDialog newInstance(String title, String message, String buttonCaption) {
        Bundle args = new Bundle(3);
        args.putString(InfoDialog.ARG_TITLE, title);
        args.putString(InfoDialog.ARG_MESSAGE, message);
        args.putString(InfoDialog.ARG_BUTTON_CAPTION, buttonCaption);

        InfoDialog infoDialog = new InfoDialog();
        infoDialog.setArguments(args);

        return infoDialog;
    }

    private EventBusPoster mEventBusPoster;

    private InfoViewMvc mInfoViewMvc;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mEventBusPoster = getControllerCompositionRoot().getEventBusPoster();
        mInfoViewMvc = getControllerCompositionRoot().getViewMvcFactory().newInfoViewMvc();

        setCancelable(true);

        initViewMvc();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(mInfoViewMvc.getRootView());
        return dialogBuilder.create();
    }

    private void initViewMvc() {
        mInfoViewMvc.setTitle(getArguments().getString(ARG_TITLE));
        mInfoViewMvc.setMessage(getArguments().getString(ARG_MESSAGE));
        mInfoViewMvc.setDismissCaption(getArguments().getString(ARG_BUTTON_CAPTION));
        mInfoViewMvc.registerListener(this);
    }

    @Override
    public void onDismissClicked() {
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mEventBusPoster.post(new InfoDialogDismissedEvent(getDialogId()));
    }

}
