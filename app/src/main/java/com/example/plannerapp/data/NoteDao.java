package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.plannerapp.model.Note;

import java.util.List;

@Dao
public interface NoteDao {
//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertNote(Note note);

    @Query("Select * from note")
    List<Note> getAllNotes();

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("Select * from note where id_FkUser=:userId")
    List<Note> getAllNotesForUser(Integer userId);

    @Query("Select * from note where idNote=:noteId")
    Note getNoteById(Integer noteId);
}
