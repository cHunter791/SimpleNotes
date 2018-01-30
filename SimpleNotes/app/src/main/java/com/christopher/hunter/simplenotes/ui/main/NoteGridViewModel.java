package com.christopher.hunter.simplenotes.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.christopher.hunter.simplenotes.data.local.db.AppDatabase;
import com.christopher.hunter.simplenotes.data.local.db.Note;

import java.util.List;

public class NoteGridViewModel extends AndroidViewModel {

    private static final String TAG = "NoteGridViewModel";

    private final LiveData<List<Note>> noteList;

    private AppDatabase appDatabase;

    public NoteGridViewModel(Application application) {
        super(application);
        Log.d(TAG, "NoteGridViewModel: starts");

        appDatabase = AppDatabase.getDatabase(this.getApplication());
        noteList = appDatabase.noteModel().getAllNotes();
    }

    LiveData<List<Note>> getNoteList() {
        Log.d(TAG, "getNoteList: starts");
        return noteList;
    }

    void deleteNote(Note note) {
        Log.d(TAG, "deleteNote: starts");
        new deleteAsyncTask(appDatabase).execute(note);
    }

    // TODO : Replace with RxJava?
    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            db.noteModel().deleteNote(params[0]);
            return null;
        }
    }
}
