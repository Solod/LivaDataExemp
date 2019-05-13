package com.solodilov.lifecycle.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.solodilov.lifecycle.model.SearchRequests;

@Database(entities = {SearchRequests.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchRequestDao searchRequestDao();
}
