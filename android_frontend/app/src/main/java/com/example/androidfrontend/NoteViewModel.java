package com.example.androidfrontend;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

// PUBLIC_INTERFACE
public class NoteViewModel extends AndroidViewModel {
    public enum SortField { TITLE, DATE }
    private final NoteRepository repository;
    private MutableLiveData<List<Note>> noteListLive = new MutableLiveData<>();
    private SortField currentSort = SortField.DATE;

    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        repository.getAllNotes().observeForever(this::setNotesList);
    }

    private void setNotesList(List<Note> notes) {
        if (currentSort == SortField.TITLE) {
            Collections.sort(notes, Comparator.comparing(Note::getTitle, String.CASE_INSENSITIVE_ORDER));
        } else {
            Collections.sort(notes, (a, b) -> Long.compare(b.getCreatedAt(), a.getCreatedAt()));
        }
        noteListLive.postValue(notes);
    }

    // PUBLIC_INTERFACE
    public LiveData<List<Note>> getNotes() {
        return noteListLive;
    }

    // PUBLIC_INTERFACE
    public LiveData<Note> getNoteById(long id) {
        return repository.getNoteById(id);
    }

    public void searchNotes(String query) {
        repository.searchNotes(query).observeForever(this::setNotesList);
    }

    public void sortBy(SortField sortField) {
        this.currentSort = sortField;
        repository.getAllNotes().observeForever(this::setNotesList);
    }

    public void insert(Note note) { repository.insert(note); }
    public void update(Note note) { repository.update(note); }
    public void delete(Note note) { repository.delete(note); }
    public LiveData<List<Note>> getNotesByCategory(String category) {
        return repository.getNotesByCategory(category);
    }
}
