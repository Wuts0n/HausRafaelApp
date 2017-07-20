package me.wuts0n.hausrafaelapp.utils;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import me.wuts0n.hausrafaelapp.R;

public class DialogUtils {


    public static void createCopyDialog(final Context context,
                                        final CharSequence title,
                                        final CharSequence text) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(R.array.copy_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                ClipboardManager clipboard = (ClipboardManager)
                                        context.getSystemService(Context.CLIPBOARD_SERVICE);
                                clipboard.setPrimaryClip(
                                        ClipData.newPlainText(title, text.toString()));
                                Toast.makeText(context,
                                        R.string.copy_successful,
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

}
