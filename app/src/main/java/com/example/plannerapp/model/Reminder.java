package com.example.plannerapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "reminder", foreignKeys = {
        @ForeignKey(
                entity = Event.class,
                parentColumns = "idEvent",
                childColumns = "id_FkEvent",
                onDelete = CASCADE,
                onUpdate = CASCADE
        )},
        indices = {@Index(value ={"idReminder", "id_FkEvent"}, unique = true)})
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int idReminder;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "id_FkEvent")
    private long id_FkEvent;

    public Reminder(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public int getIdReminder() {
        return idReminder;
    }

    public void setIdReminder(int idReminder) {
        this.idReminder = idReminder;
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

    public long getId_FkEvent() {
        return id_FkEvent;
    }

    public void setId_FkEvent(long id_FkEvent) {
        this.id_FkEvent = id_FkEvent;
    }
}
