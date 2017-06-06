package com.christopher.hunter.simplenotes.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.db.Note;
import com.christopher.hunter.simplenotes.viewmodel.NoteGridViewModel;

import java.util.ArrayList;
import java.util.List;

/*
    TODO: Put in tests
*/

// LifecycleActivity is a temporary measure from Google. Will be deprecated when Lifecycle is fully released
// as support library fragments and activities will implement LifecycleOwner.
public class MainActivity extends LifecycleActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "MainActivity";

    private NoteGridViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteAddActivity.class));
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Note>(), this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(NoteGridViewModel.class);
        viewModel.getNoteList().observe(MainActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                recyclerViewAdapter.addItems(notes);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: starts");

        Note note = (Note) v.getTag();
        Intent intent = new Intent(this, NoteEditActivity.class);
        intent.putExtra(Note.class.getSimpleName(), note);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d(TAG, "onLongClick: starts");

        Note note = (Note) v.getTag();
        viewModel.deleteNote(note);
        return true;
    }
}
