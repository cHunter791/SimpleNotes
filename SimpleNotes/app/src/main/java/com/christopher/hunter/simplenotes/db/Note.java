package com.christopher.hunter.simplenotes.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String title;
    private String content;

    public Note (int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Note: ID\t " + id + " Title\t " + title + " Content\t " + content + "\n";
    }
}
