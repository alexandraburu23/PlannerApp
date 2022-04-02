package com.example.plannerapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithTask {
    @Embedded
    private User user;
    @Relation(
            parentColumn = "idUser",
            entityColumn ="id_FkUser"
    )
    private List<Task> tasks;

    public UserWithTask(User user, List<Task> tasks) {
        this.user = user;
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
