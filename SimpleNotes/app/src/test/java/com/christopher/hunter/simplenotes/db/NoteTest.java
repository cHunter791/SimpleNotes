package com.christopher.hunter.simplenotes.db;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NoteTest {

    public static final String TITLE = "Test Title";
    public static final String CONTENT = "Test Content";

    /*
        Test we can create a note
     */
    @Test
    public void testCreatingNote() {
        Note note = new Note(0, TITLE, CONTENT);
        assertEquals(TITLE, note.getTitle());
        assertEquals(CONTENT, note.getContent());
    }
}