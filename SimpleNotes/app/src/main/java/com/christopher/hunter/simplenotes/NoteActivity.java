package com.christopher.hunter.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import io.realm.Realm;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";

    Realm realm;
    Long passedId;
    Note note;
    EditText noteTitle;
    EditText noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        noteTitle = (EditText) findViewById(R.id.plain_text_title);
        noteContent = (EditText) findViewById(R.id.plain_text_content);

        Intent intent = getIntent();
        passedId = (Long) intent.getSerializableExtra(Note.class.getSimpleName());

        if (passedId != null) {
            note = realm.where(Note.class).equalTo("id", passedId).findFirst();

            noteTitle.setText(note.getTitle());
            noteContent.setText(note.getContent());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: ");

        if (passedId != null) {
            updateNote();
        } else if (noteTitle.getText() != null || noteContent.getText() != null){
            createNote();
        }
        realm.close();
    }

    public void updateNote() {

        Log.d(TAG, "updateNote: updating existing note");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                note.setTitle(noteTitle.getText().toString());
                note.setContent(noteContent.getText().toString());
            }
        });
    }

    public void createNote() {

        Log.d(TAG, "createNote: creating a new note");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Note newNote = realm.createObject(Note.class);

                try {
                    newNote.setId(realm.where(Note.class).max("id").intValue() + 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return;
                }

                newNote.setTitle(noteTitle.getText().toString());
                newNote.setContent(noteContent.getText().toString());

                Log.d(TAG, "execute: created note: " + newNote.getTitle() + "\n" + newNote.getContent());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
