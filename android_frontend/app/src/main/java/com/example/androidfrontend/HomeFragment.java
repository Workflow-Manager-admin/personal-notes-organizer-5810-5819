package com.example.androidfrontend;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class HomeFragment extends Fragment {
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    public HomeFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_notes);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        TextInputEditText searchBox = root.findViewById(R.id.search_box);

        noteViewModel = new ViewModelProvider(this)
                .get(NoteViewModel.class);
        noteViewModel.getNotes().observe(getViewLifecycleOwner(), notes -> noteAdapter.submitList(notes));

        noteAdapter.setOnNoteClickListener(note -> {
            Intent intent = new Intent(getContext(), NoteEditActivity.class);
            intent.putExtra("noteId", note.getId());
            startActivity(intent);
        });

        searchBox.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                noteViewModel.searchNotes(s.toString());
            }
            @Override public void afterTextChanged(android.text.Editable s){}
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_title) {
            noteViewModel.sortBy(NoteViewModel.SortField.TITLE);
            return true;
        } else if (id == R.id.action_sort_date) {
            noteViewModel.sortBy(NoteViewModel.SortField.DATE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
