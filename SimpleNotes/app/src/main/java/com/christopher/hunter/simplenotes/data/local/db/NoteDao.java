package com.christopher.hunter.simplenotes.data.local.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {

    @Query("select * from Note")
    LiveData<List<Note>> getAllNotes();

    @Query("select * from Note where id = :id")
    Note getNoteById(String id);

    @Insert(onConflict = REPLACE)
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update(onConflict = REPLACE)
    void updateNote(Note note);
}
