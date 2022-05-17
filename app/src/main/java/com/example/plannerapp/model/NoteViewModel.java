package com.example.plannerapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plannerapp.data.NoteRepository;
import com.example.plannerapp.data.UserRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    public static NoteRepository repository;
    public final List<Note> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository =  new NoteRepository(application);
        allNotes = repository.getAllData();
    }

    public List<Note> getAllNotes(){
        return allNotes;
    }
    public void insertNote(Note note){
        repository.insertNote(note);
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
    }

    public void updateNote(Note note){repository.updateNote(note);}

    public List<Note> getAllNotesForUser(Integer userId) { return repository.getAllNotesForUser(userId);}

    public Note getNoteById(Integer noteId) { return repository.getNoteById(noteId);}

}
