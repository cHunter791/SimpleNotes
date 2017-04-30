package com.christopher.hunter.simplenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Christopher on 14/04/2017.
 */

public class NoteAdapter extends BaseAdapter {

    private static final String TAG = "NoteAdapter";

    private LayoutInflater inflater;
    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int getCount() {
        if (notes == null) {
            return 0;
        }
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        if (notes == null || notes.get(position) == null) {
            return null;
        }
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Note note = notes.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.plain_text_note_display, null);
        }

        if (note != null) {
            final TextView titleTextView = (TextView) convertView.findViewById(R.id.plain_text_title);
            final TextView contentTextView = (TextView) convertView.findViewById(R.id.plain_text_content);

            titleTextView.setText(note.getTitle());
            contentTextView.setText(note.getContent());
        }

        return convertView;
    }
}
