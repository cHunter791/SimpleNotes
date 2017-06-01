package com.christopher.hunter.simplenotes.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.db.Note;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Note> noteModelList;
    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;

    RecyclerViewAdapter(List<Note> noteModelList, View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        this.noteModelList = noteModelList;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_display, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Note noteModel = noteModelList.get(position);
        holder.titleTextView.setText(noteModel.getTitle());
        holder.contentTextView.setText(noteModel.getContent());
        holder.itemView.setTag(noteModel);
        holder.itemView.setOnClickListener(clickListener);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    void addItems(List<Note> noteModelList) {
        this.noteModelList = noteModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView contentTextView;

        RecyclerViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.plain_text_title);
            contentTextView = (TextView) view.findViewById(R.id.plain_text_content);
        }
    }
}
