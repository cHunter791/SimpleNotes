package com.christopher.hunter.simplenotes;

import java.io.Serializable;

import io.realm.RealmObject;

import static android.R.attr.id;

/**
 * Created by Christopher on 27/04/2017.
 */

public class PlainTextNote extends RealmObject implements Note, Serializable {
    public static final long serialVersionUID = 12042017L;

    private String title;
    private String content;

    public PlainTextNote() {

    }

    public PlainTextNote(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Plain Text Note: ID\t " + id + " Title\t " + title + " Content\t " + content + "\n";
    }
}
