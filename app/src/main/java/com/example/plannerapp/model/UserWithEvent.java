package com.example.plannerapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithEvent {
    @Embedded private User user;
    @Relation(
            parentColumn = "idUser",
            entityColumn = "id_FkUser"
    )
    private List<Event> events;

    public UserWithEvent(User user, List<Event> events) {
        this.user = user;
        this.events = events;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
