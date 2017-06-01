package com.christopher.hunter.simplenotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.christopher.hunter.simplenotes.db.AppDatabase;
import com.christopher.hunter.simplenotes.db.Note;

public class NoteAddViewModel extends AndroidViewModel {

    private static final String TAG = "NoteAddViewModel";

    private AppDatabase appDatabase;

    public NoteAddViewModel(Application application) {
        super(application);
        Log.d(TAG, "NoteAddViewModel: starts");

        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addNote(Note note) {
        Log.d(TAG, "addNote: starts");
        new addAsyncTask(appDatabase).execute(note);
    }

    private static class addAsyncTask extends AsyncTask<Note, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            db.noteModel().addNote(params[0]);
            return null;
        }
    }
}
