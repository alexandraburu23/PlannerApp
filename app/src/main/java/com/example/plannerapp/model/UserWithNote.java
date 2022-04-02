package com.example.plannerapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithNote {
    @Embedded private User user;
    @Relation(
            parentColumn = "idUser",
            entityColumn ="id_FkUser"
    )
    private List<Note> notes;

    public UserWithNote(User user, List<Note> notes) {
        this.user = user;
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
