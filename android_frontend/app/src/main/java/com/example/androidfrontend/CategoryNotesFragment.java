package com.example.androidfrontend;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryNotesFragment extends Fragment {
    private static final String ARG_CATEGORY = "category";

    private NoteAdapter adapter;
    private String category;
    private NoteViewModel noteViewModel;

    public static CategoryNotesFragment newInstance(String category) {
        CategoryNotesFragment fragment = new CategoryNotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_category_notes, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_notes_by_category);
        adapter = new NoteAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        category = getArguments() != null ? getArguments().getString(ARG_CATEGORY, "All") : "All";
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getNotesByCategory(category).observe(getViewLifecycleOwner(), notes -> adapter.submitList(notes));

        adapter.setOnNoteClickListener(note -> {
            // Open for edit
            android.content.Intent intent = new android.content.Intent(getContext(), NoteEditActivity.class);
            intent.putExtra("noteId", note.getId());
            startActivity(intent);
        });

        return root;
    }
}
