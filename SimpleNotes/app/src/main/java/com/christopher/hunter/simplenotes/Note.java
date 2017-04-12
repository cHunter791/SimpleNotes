package com.christopher.hunter.simplenotes;

import java.io.Serializable;

/**
 * Created by Christopher on 12/04/2017.
 */

class Note implements Serializable {
    public static final long serialVersionUID = 12042017L;

    private long _Id;
    private String title;
    private String content;

    public Note(long _Id, String title, String content) {
        this._Id = _Id;
        this.title = title;
        this.content = content;
    }

    public long get_Id() {
        return _Id;
    }

    public void set_Id(long _Id) {
        this._Id = _Id;
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
        return "Note{" +
                "_Id=" + _Id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
