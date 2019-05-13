package com.solodilov.lifecycle;

import android.app.Application;

import androidx.room.Room;

import com.solodilov.lifecycle.database.AppDatabase;

public class LifeCycleApplication extends Application {
    private static LifeCycleApplication instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "room_database")
                .build();
    }

    public static LifeCycleApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
