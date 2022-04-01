package com.example.plannerapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class EventWithReminder {
    @Embedded
    private Event event;
    @Relation(
            parentColumn = "idEvent",
            entityColumn = "id_FkEvent"
    )
    private List<Reminder> reminders;

    public EventWithReminder(Event event, List<Reminder> reminders) {
        this.event = event;
        this.reminders = reminders;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }
}
