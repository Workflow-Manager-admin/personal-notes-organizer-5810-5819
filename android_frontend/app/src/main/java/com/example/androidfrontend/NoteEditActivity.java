package com.example.androidfrontend;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.view.Menu;
import android.view.MenuItem;

public class NoteEditActivity extends AppCompatActivity {

    private EditText titleEdit;
    private EditText contentEdit;
    private NoteViewModel noteViewModel;
    private long noteId = -1L;
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.App_Theme);
        setContentView(R.layout.activity_note_edit);

        titleEdit = findViewById(R.id.edit_note_title);
        contentEdit = findViewById(R.id.edit_note_content);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        if (getIntent() != null && getIntent().hasExtra("noteId")) {
            noteId = getIntent().getLongExtra("noteId", -1L);
            noteViewModel.getNoteById(noteId).observe(this, note -> {
                if (note != null) {
                    currentNote = note;
                    titleEdit.setText(note.getTitle());
                    contentEdit.setText(note.getContent());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);
        if (noteId == -1L) {
            menu.findItem(R.id.action_delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save:
                saveNote();
                return true;
            case R.id.action_delete:
                deleteNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = titleEdit.getText().toString().trim();
        String content = contentEdit.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, "Title required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (noteId == -1L) {
            Note note = new Note(title, content, "Personal"); // Default to Personal category
            noteViewModel.insert(note);
        } else {
            currentNote.setTitle(title);
            currentNote.setContent(content);
            noteViewModel.update(currentNote);
        }
        finish();
    }

    private void deleteNote() {
        if (noteId != -1L && currentNote != null) {
            noteViewModel.delete(currentNote);
            finish();
        }
    }
}
