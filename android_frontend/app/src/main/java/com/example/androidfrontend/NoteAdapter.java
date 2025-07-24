package com.example.androidfrontend;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteViewHolder> {
    private OnNoteClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.bind(note);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                listener.onNoteClick(note);
        });
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        this.listener = listener;
    }
}
