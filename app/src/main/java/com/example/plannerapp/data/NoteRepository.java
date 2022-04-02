package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.util.MyRoomDatabase;

import java.util.List;

public class NoteRepository {

    private final NoteDao noteDao;
    private final List<Note> allNotes;

    public NoteRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }
    public List<Note> getAllData(){
        return allNotes;
    }
}
