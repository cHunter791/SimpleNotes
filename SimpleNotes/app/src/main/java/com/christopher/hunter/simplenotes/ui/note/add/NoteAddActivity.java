package com.christopher.hunter.simplenotes.ui.note.add;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.data.local.db.Note;

// TODO Tests

public class NoteAddActivity extends AppCompatActivity {

    private NoteAddViewModel mViewModel;
    private Note mNote;

    private EditText mTitleText;
    private EditText mContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mTitleText = findViewById(R.id.plain_text_title);
        mContentText = findViewById(R.id.plain_text_content);

        mViewModel = ViewModelProviders.of(this).get(NoteAddViewModel.class);

        Intent intent = getIntent();
        if (intent != null) {
            mNote = (Note) intent.getSerializableExtra(Note.class.getSimpleName());
        }
        if (mNote != null) {
            mTitleText.setText(mNote.getTitle());
            mContentText.setText(mNote.getContent());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            mViewModel.addNote(new Note(0, mTitleText.getText().toString(), mContentText.getText().toString()));
            finish();
        });
    }
}
