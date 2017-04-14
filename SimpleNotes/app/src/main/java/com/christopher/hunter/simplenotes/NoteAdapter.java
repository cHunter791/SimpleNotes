package com.christopher.hunter.simplenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Christopher on 14/04/2017.
 */

public class NoteAdapter extends BaseAdapter {

    private static final String TAG = "NoteAdapter";

    private Context context;
    private Note[] notes;

    public NoteAdapter(Context context, Note[] notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Note note = notes[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.plain_text_note, null);
        }

        final TextView titleTextView = (TextView) convertView.findViewById(R.id.plain_text_title);
        final TextView contentTextView = (TextView) convertView.findViewById(R.id.plain_text_content);

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());

        return convertView;
    }
}
