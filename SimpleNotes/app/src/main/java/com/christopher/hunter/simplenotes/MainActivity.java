package com.christopher.hunter.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Test notes
        Note testNote = new PlainTextNote(1, "Test 1", "This is a test");
        notes.add(testNote);
        Note testNote2 = new PlainTextNote(2, "Test 2", "This is also test");
        notes.add(testNote2);
        Note testNote3 = new PlainTextNote(3, "Test 3", "This is a very very very very very very very very " +
                "very very very very very very very very very very very very very very " +
                "loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong test");
        notes.add(testNote3);

        final GridView noteGrid = (GridView) findViewById(R.id.note_grid);

        final NoteAdapter noteAdapter = new NoteAdapter(this, notes);
        noteGrid.setAdapter(noteAdapter);

        noteGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                noteRequest(notes.get(position));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteRequest(null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void noteRequest(Note note) {
        Log.d(TAG, "taskEditRequest: starts");

        Intent intent = new Intent(this, NoteActivity.class);
        if (note != null) {
            intent.putExtra(Note.class.getSimpleName(), (Serializable) note);
            startActivity(intent);
        } else {
            startActivity(intent);
        }
    }
}
