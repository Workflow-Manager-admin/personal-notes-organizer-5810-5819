package com.example.androidfrontend;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// PUBLIC_INTERFACE
public class NoteRepository {
    private final NoteDao noteDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // PUBLIC_INTERFACE
    public NoteRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotes() { return noteDao.getAllNotes(); }

    public LiveData<Note> getNoteById(long id) {
        return noteDao.getNoteById(id);
    }

    public LiveData<List<Note>> searchNotes(String query) {
        return noteDao.searchNotes(query);
    }

    public LiveData<List<Note>> getNotesByCategory(String category) {
        if ("All".equals(category)) return noteDao.getAllNotes();
        return noteDao.getNotesByCategory(category);
    }

    public void insert(Note note) {
        executor.execute(() -> noteDao.insert(note));
    }

    public void update(Note note) {
        executor.execute(() -> noteDao.update(note));
    }

    public void delete(Note note) {
        executor.execute(() -> noteDao.delete(note));
    }
}
