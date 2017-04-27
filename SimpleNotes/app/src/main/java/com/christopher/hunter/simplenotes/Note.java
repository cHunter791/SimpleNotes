package com.christopher.hunter.simplenotes;

/**
 * Created by Christopher on 12/04/2017.
 */

interface Note {

    String title = "";

    long getId();
    void setId(long id);

    String getTitle();
    void setTitle(String title);

    String getContent();
    void setContent(String content);
}
