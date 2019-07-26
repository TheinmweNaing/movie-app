package com.example.movieapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.movieapp.model.AppDatabase;

import java.util.concurrent.Executors;

public class MainApplication extends Application {

    private static AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        //database = Room.databaseBuilder(this, AppDatabase.class, "pgm").build();
    }

    public static AppDatabase getDatabase(final Context context) {
        if (database == null) {
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                    .allowMainThreadQueries()
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                        }
                    })
                    .build();

        }
        return database;
    }
}
