package com.christopher.hunter.simplenotes.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.data.local.db.Note;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Note> mNoteModelList;
    private View.OnClickListener mClickListener;
    private View.OnLongClickListener mLongClickListener;

    RecyclerViewAdapter(List<Note> noteModelList, View.OnClickListener clickListener, View.OnLongClickListener longClickListener) {
        mNoteModelList = noteModelList;
        mClickListener = clickListener;
        mLongClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_display, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Note noteModel = mNoteModelList.get(position);
        holder.mTitleTextView.setText(noteModel.getTitle());
        holder.mContentTextView.setText(noteModel.getContent());
        holder.itemView.setTag(noteModel);
        holder.itemView.setOnClickListener(mClickListener);
        holder.itemView.setOnLongClickListener(mLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mNoteModelList.size();
    }

    void addItems(List<Note> noteModelList) {
        mNoteModelList = noteModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mContentTextView;

        RecyclerViewHolder(View view) {
            super(view);
            mTitleTextView = view.findViewById(R.id.plain_text_title);
            mContentTextView = view.findViewById(R.id.plain_text_content);
        }
    }
}
