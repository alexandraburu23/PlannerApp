package com.example.plannerapp.model;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "event", indices = {@Index(value ={"idEvent", "id_FkUser"}, unique = true)})
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int idEvent;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "location")
    private String location;

    @ForeignKey(
            entity = User.class,
            parentColumns = "idUser",
            childColumns = "id_FkUser",
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    private long id_FkUser;

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Event(String title, String description, LocalDate date, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public long getId_FkUser() {
        return id_FkUser;
    }

    public void setId_FkUser(long id_FkUser) {
        this.id_FkUser = id_FkUser;
    }
}
