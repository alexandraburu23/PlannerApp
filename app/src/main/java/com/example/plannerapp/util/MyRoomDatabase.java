package com.example.plannerapp.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.plannerapp.data.UserDao;
import com.example.plannerapp.model.User;
import com.example.plannerapp.data.NoteDao;
import com.example.plannerapp.model.Note;

@Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "person_db";
    private static MyRoomDatabase instance;

    public static synchronized MyRoomDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


    public abstract UserDao userDao();
}
