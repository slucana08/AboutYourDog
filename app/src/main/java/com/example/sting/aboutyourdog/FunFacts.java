package com.example.sting.aboutyourdog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class FunFacts extends DialogFragment {

    public static MessagesIncomplete newInstance(String message, String dismiss) {
        MessagesIncomplete frag = new MessagesIncomplete();
        Bundle args = new Bundle();
        args.putString("message", message);
        args.putString("dismiss", dismiss);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message = getArguments().getString("message");
        String dismiss = getArguments().getString("dismiss");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        TextView msg = new TextView(getActivity());
        String text = message;
        msg.setText(text);
        builder.setMessage(msg.getText())
                .setCancelable(false)
                .setPositiveButton(dismiss, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
