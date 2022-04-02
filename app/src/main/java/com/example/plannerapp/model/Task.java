package com.example.plannerapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "task", foreignKeys =
        {@ForeignKey(
                entity = User.class,
                parentColumns = "idUser",
                childColumns = "id_FkUser",
                onDelete = CASCADE,
                onUpdate = CASCADE
        )},
        indices = {@Index(value ={"idTask", "id_FkUser"}, unique = true)})
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int idTask;

    @ColumnInfo(name = "id_FkUser")
    private long id_FkUser;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "deadline")
    private LocalDate deadline;

    public Task(String description, String status, String category, LocalDate deadline) {
        this.description = description;
        this.status = status;
        this.category = category;
        this.deadline = deadline;
    }

    public long getId_FkUser() {
        return id_FkUser;
    }

    public void setId_FkUser(long id_FkUser) {
        this.id_FkUser = id_FkUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
}
