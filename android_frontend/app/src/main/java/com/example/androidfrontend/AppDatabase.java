package com.example.androidfrontend;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// PUBLIC_INTERFACE
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract NoteDao noteDao();

    // PUBLIC_INTERFACE
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "notes.db"
                        ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
