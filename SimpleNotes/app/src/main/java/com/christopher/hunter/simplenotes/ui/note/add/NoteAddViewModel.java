package com.christopher.hunter.simplenotes.ui.note.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.christopher.hunter.simplenotes.data.local.db.AppDatabase;
import com.christopher.hunter.simplenotes.data.local.db.Note;

public class NoteAddViewModel extends AndroidViewModel {

    private static final String TAG = "NoteAddViewModel";

    private AppDatabase mAppDatabase;

    public NoteAddViewModel(Application application) {
        super(application);
        Log.d(TAG, "NoteAddViewModel: starts");

        this.mAppDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    void addNote(Note note) {
        Log.d(TAG, "addNote: starts");
        new addAsyncTask(mAppDatabase).execute(note);
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
