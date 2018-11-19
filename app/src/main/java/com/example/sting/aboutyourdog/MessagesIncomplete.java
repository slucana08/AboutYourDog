package com.example.sting.aboutyourdog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

/**
 * This class calls a dialog box that tells you how many correct answers you have
 * From it you can either try th test again or see the answers
 */

public class MessagesIncomplete extends DialogFragment {

    public static MessagesIncomplete newInstance(String message, String dismiss, String action,
                                                 String facts) {
        MessagesIncomplete frag = new MessagesIncomplete();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("action", action);
        args.putString("dismiss", dismiss);
        args.putString("facts", facts);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message = getArguments().getString("message");
        String action = getArguments().getString("action");
        String dismiss = getArguments().getString("dismiss");
        final String facts = getArguments().getString("facts");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        TextView msg = new TextView(getActivity());
        String text = message;
        msg.setText(text);
        builder.setMessage(msg.getText())
                .setTitle(getString(R.string.app_name))
                .setCancelable(false)
                .setPositiveButton(dismiss, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFragment factsDialog = FunFacts.newInstance(facts,
                                getString(R.string.nice));
                        factsDialog.show(getFragmentManager(), "FactsDialog");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
