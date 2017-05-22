package com.christopher.hunter.simplenotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Chunt on 22/05/2017.
 */

public class AppDialog extends DialogFragment {

    private static final String TAG = "AppDialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: starts");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Realm realm = Realm.getDefaultInstance();
        final Bundle args = getArguments();
        final long id;
        final String title;

        if (args != null) {
            id = args.getLong("ID");
            title = args.getString("TITLE");
        } else {
            throw new IllegalArgumentException("Must pass ID and TITLE in the bundle");
        }

        builder.setMessage("Would you like to delete note '" + title + "'?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                RealmResults<Note> result = realm.where(Note.class).equalTo("id", id).findAll();
                                result.deleteAllFromRealm();
                            }
                        });
                        realm.close();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm.close();
                    }
                });

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(TAG, "onDismiss: starts");

        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}
