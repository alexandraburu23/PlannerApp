package com.example.plannerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "note", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "idUser",
                childColumns = "id_FkUser",
                onDelete = CASCADE,
                onUpdate = CASCADE
        )},
        indices = {@Index(value ={"idNote", "id_FkUser"}, unique = true)})

public class Note {
    @PrimaryKey(autoGenerate = true)
    private int idNote;

    @ColumnInfo(name = "id_FkUser")
    private long id_FkUser;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;


    public long getId_FkUser() {
        return id_FkUser;
    }

    public void setId_FkUser(long id_FkUser) {
        this.id_FkUser = id_FkUser;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Note(Note note) {
        this.idNote = note.getIdNote();
        this.id_FkUser = note.getId_FkUser();
        this.title = note.getTitle();
        this.content = note.getContent();
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}