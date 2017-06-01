package com.christopher.hunter.simplenotes.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.db.Note;
import com.christopher.hunter.simplenotes.viewmodel.NoteEditViewModel;

public class NoteEditActivity extends AppCompatActivity {

    private static final String TAG = "NoteEditActivity";

    private NoteEditViewModel viewModel;
    private Note note;

    private EditText titleText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleText = (EditText) findViewById(R.id.plain_text_title);
        contentText = (EditText) findViewById(R.id.plain_text_content);

        viewModel = ViewModelProviders.of(this).get(NoteEditViewModel.class);

        Intent intent = getIntent();
        if (intent != null) {
            note = (Note) intent.getSerializableExtra(Note.class.getSimpleName());
        }
        if (note != null) {
            titleText.setText(note.getTitle());
            contentText.setText(note.getContent());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.updateNote(new Note(note.getId(), titleText.getText().toString(), contentText.getText().toString()));
                finish();
            }
        });
    }
}
