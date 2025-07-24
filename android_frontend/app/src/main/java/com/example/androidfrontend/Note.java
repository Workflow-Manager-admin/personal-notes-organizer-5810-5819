package com.example.androidfrontend;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

// PUBLIC_INTERFACE
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String content;
    private String category;
    private long createdAt;

    public Note(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = System.currentTimeMillis();
    }

    public void setId(long id) { this.id = id; }
    // PUBLIC_INTERFACE
    public long getId() { return id; }
    // PUBLIC_INTERFACE
    public String getTitle() { return title; }
    // PUBLIC_INTERFACE
    public void setTitle(String title) { this.title = title; }
    // PUBLIC_INTERFACE
    public String getContent() { return content; }
    // PUBLIC_INTERFACE
    public void setContent(String content) { this.content = content; }
    // PUBLIC_INTERFACE
    public String getCategory() { return category; }
    // PUBLIC_INTERFACE
    public void setCategory(String category) { this.category = category; }
    // PUBLIC_INTERFACE
    public long getCreatedAt() { return createdAt; }
    // PUBLIC_INTERFACE
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    // PUBLIC_INTERFACE
    public String getSnippet() {
        if (content == null) return "";
        return content.length() > 64 ? content.substring(0, 64) + "…" : content;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Note)) return false;
        Note n = (Note)o;
        return id == n.id &&
                Objects.equals(title, n.title) &&
                Objects.equals(content, n.content) &&
                Objects.equals(category, n.category) &&
                createdAt == n.createdAt;
    }
}
