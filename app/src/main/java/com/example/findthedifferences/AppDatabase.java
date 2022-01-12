package com.example.findthedifferences;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Level.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LevelDao levelDao();
}
