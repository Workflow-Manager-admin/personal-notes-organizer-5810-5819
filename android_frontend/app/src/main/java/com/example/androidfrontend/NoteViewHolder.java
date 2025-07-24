package com.example.androidfrontend;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    private final TextView snippetView;

    public NoteViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.text_note_title);
        snippetView = itemView.findViewById(R.id.text_note_snippet);
    }

    public void bind(Note note) {
        titleView.setText(note.getTitle());
        snippetView.setText(note.getSnippet());
    }
}
