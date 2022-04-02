package com.example.plannerapp.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.plannerapp.data.EventDao;
import com.example.plannerapp.data.ReminderDao;
import com.example.plannerapp.data.TaskDao;
import com.example.plannerapp.data.UserDao;
import com.example.plannerapp.model.Converters;
import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.User;
import com.example.plannerapp.data.NoteDao;
import com.example.plannerapp.model.Note;

@Database(entities = {User.class, Event.class, Reminder.class, Note.class, Task.class}, exportSchema = false, version = 1)
@TypeConverters({Converters.class})
public abstract class MyRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "person_db";
    private static MyRoomDatabase instance;

    public static synchronized MyRoomDatabase getDatabase(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



    public abstract UserDao userDao();
    public abstract EventDao eventDao();
    public abstract ReminderDao reminderDao();
    public abstract NoteDao noteDao();
    public abstract TaskDao taskDao();
}
