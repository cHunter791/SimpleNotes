package com.christopher.hunter.simplenotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.christopher.hunter.simplenotes.db.AppDatabase;
import com.christopher.hunter.simplenotes.db.Note;

public class NoteEditViewModel extends AndroidViewModel {

    private static final String TAG = "NoteAddViewModel";

    private AppDatabase appDatabase;

    public NoteEditViewModel(Application application) {
        super(application);
        Log.d(TAG, "NoteAddViewModel: starts");

        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void updateNote(Note note) {
        Log.d(TAG, "updateNote: starts");
        new updateAsyncTask(appDatabase).execute(note);
    }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private AppDatabase db;

        updateAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            db.noteModel().updateNote(params[0]);
            return null;
        }
    }
}
