package com.example.androidfrontend;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

// PUBLIC_INTERFACE
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    LiveData<Note> getNoteById(long id);

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :search || '%' OR content LIKE '%' || :search || '%' ORDER BY createdAt DESC")
    LiveData<List<Note>> searchNotes(String search);

    @Query("SELECT * FROM notes WHERE category = :category ORDER BY createdAt DESC")
    LiveData<List<Note>> getNotesByCategory(String category);
}
