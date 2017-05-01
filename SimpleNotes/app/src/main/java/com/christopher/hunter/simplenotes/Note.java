package com.christopher.hunter.simplenotes;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Christopher on 27/04/2017.
 */

public class Note extends RealmObject implements Serializable {
    public static final long serialVersionUID = 12042017L;

    private long id;
    private String title;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Plain Text Note: ID\t " + id + " Title\t " + title + " Content\t " + content + "\n";
    }
}
