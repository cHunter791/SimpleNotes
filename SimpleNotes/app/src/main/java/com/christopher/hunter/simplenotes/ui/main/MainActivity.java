package com.christopher.hunter.simplenotes.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.ui.note.add.NoteAddActivity;
import com.christopher.hunter.simplenotes.ui.note.edit.NoteEditActivity;
import com.christopher.hunter.simplenotes.data.local.db.Note;

import java.util.ArrayList;

/*
    TODO : Put in tests
    TODO : Proper MVVM structure (base files)
    TODO : Dagger for dependency injection
    TODO : Data binding
    TODO : Navigation look at Simple Backstack (https://github.com/Zhuinden/simple-stack)
    TODO : Fancy animations?
*/
public class MainActivity extends AppCompatActivity
        implements LifecycleOwner, View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "MainActivity";

    private NoteGridViewModel mViewModel;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, NoteAddActivity.class)));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>(), this, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        recyclerView.setAdapter(mRecyclerViewAdapter);

        mViewModel = ViewModelProviders.of(this).get(NoteGridViewModel.class);
        mViewModel.getNoteList().observe(MainActivity.this, notes -> mRecyclerViewAdapter.addItems(notes));
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
        mViewModel.deleteNote(note);
        return true;
    }
}
