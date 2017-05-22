package com.christopher.hunter.simplenotes;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/* TODO: Put in tests
   TODO: Tidy up code (separate out Realm code)
   TODO: Use custom linear layout (http://stackoverflow.com/questions/7545915/gridview-rows-overlapping-how-to-make-row-height-fit-the-tallest-item) to expand each row to max note height
   TODO: Delete notes by selecting with long press then pressing delete button
*/

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private static final String TAG = "MainActivity";
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        GridView noteGrid = (GridView) findViewById(R.id.note_grid);
        noteGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AppDialog dialog = new AppDialog();
                Bundle args = new Bundle();
                args.putLong("ID", loadNotes().get(position).getId());
                args.putString("TITLE", loadNotes().get(position).getTitle());
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), null);
                return true;
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
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");

        updateNoteGrid();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    @Override
    public void onDismiss(DialogInterface dialog) {
        updateNoteGrid();
    }

    private void updateNoteGrid() {
        final List<Note> notes = loadNotes();

        Log.d(TAG, "onResume: populating grid");
        GridView noteGrid = (GridView) findViewById(R.id.note_grid);

        NoteAdapter noteAdapter = new NoteAdapter(this);
        noteAdapter.setNotes(notes);

        noteGrid.setAdapter(noteAdapter);
        noteGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                noteRequest(notes.get(position).getId());
            }
        });
        noteAdapter.notifyDataSetChanged();
        noteGrid.invalidate();
    }

    private ArrayList<Note> loadNotes() {

        Log.d(TAG, "loadNotes: loading in notes");

        RealmResults<Note> notes = realm.where(Note.class).findAll();
        Log.d(TAG, "loadNotes: loaded in " + notes.size() + " note(s)");

        return new ArrayList<>(notes);
    }

    private void noteRequest(Long id) {
        Log.d(TAG, "taskEditRequest: starts");

        Intent intent = new Intent(this, NoteActivity.class);
        if (id != null) {
            intent.putExtra(Note.class.getSimpleName(), id);
            startActivity(intent);
        } else {
            startActivity(intent);
        }
    }
}
