package com.garrell.co.baseapp.screens.common.dialogs.prompt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.garrell.co.baseapp.screens.common.dialogs.BaseDialog;
import com.garrell.co.baseapp.common.eventbus.EventBusPoster;

/**
 * A dialog that can show title and message and has two buttons. User's actions performed
 * in this dialog will be posted to event bus as {@link PromptDialogDismissedEvent}.
 */
public class PromptDialog extends BaseDialog implements PromptViewMvc.PromptViewMvcListener {

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_POSITIVE_BUTTON_CAPTION = "ARG_POSITIVE_BUTTON_CAPTION";
    private static final String ARG_NEGATIVE_BUTTON_CAPTION = "ARG_NEGATIVE_BUTTON_CAPTION";

    public static PromptDialog newInstance(String title,
                                           String message,
                                           String positiveButtonCaption,
                                           String negativeButtonCaption) {
        Bundle args = new Bundle(4);
        args.putString(PromptDialog.ARG_TITLE, title);
        args.putString(PromptDialog.ARG_MESSAGE, message);
        args.putString(PromptDialog.ARG_POSITIVE_BUTTON_CAPTION, positiveButtonCaption);
        args.putString(PromptDialog.ARG_NEGATIVE_BUTTON_CAPTION, negativeButtonCaption);

        PromptDialog promptDialog = new PromptDialog();
        promptDialog.setArguments(args);

        return promptDialog;
    }
    
    private EventBusPoster eventBusPoster;
    private PromptViewMvc promptViewMvc;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        eventBusPoster = getControllerCompositionRoot().getEventBusPoster();
        promptViewMvc = getControllerCompositionRoot().getViewMvcFactory().newPromptViewMvc();

        setCancelable(true);

        initViewMvc();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(promptViewMvc.getRootView());
        return dialogBuilder.create();
    }

    private void initViewMvc() {
        promptViewMvc.setTitle(getArguments().getString(ARG_TITLE));
        promptViewMvc.setMessage(getArguments().getString(ARG_MESSAGE));
        promptViewMvc.setPositiveButtonCaption(getArguments().getString(ARG_POSITIVE_BUTTON_CAPTION));
        promptViewMvc.setNegativeButtonCaption(getArguments().getString(ARG_NEGATIVE_BUTTON_CAPTION));
        promptViewMvc.registerListener(this);
    }

    @Override
    public void onPositiveButtonClicked() {
        dismiss();
        eventBusPoster.post(new PromptDialogDismissedEvent(
                getDialogId(), PromptDialogDismissedEvent.BUTTON_POSITIVE));
    }

    @Override
    public void onNegativeButtonClicked() {
        dismiss();
        eventBusPoster.post(new PromptDialogDismissedEvent(
                getDialogId(), PromptDialogDismissedEvent.BUTTON_NEGATIVE));
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dismiss();
        eventBusPoster.post(new PromptDialogDismissedEvent(
                getDialogId(), PromptDialogDismissedEvent.BUTTON_NONE));
    }

}
