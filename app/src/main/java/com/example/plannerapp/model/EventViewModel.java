package com.example.plannerapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plannerapp.data.EventRepository;
import com.example.plannerapp.data.TaskRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    public static EventRepository repository;
    public final List<Event> allEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository =  new EventRepository(application);
        allEvents = repository.getAllData();
    }

    public List<Event> getAllEvents(){
        return allEvents;
    }
    public void insertEvent(Event event){
        repository.insertEvent(event);
    }

    public void deleteEvent(Event event){
        repository.deleteEvent(event);
    }

    public void updateEvent(Event event){repository.updateEvent(event);}

    public List<Event> getAllEventsForUser(Integer userId) { return repository.getAllEventsForUser(userId);}

    public Event getEventById(Integer eventId) { return repository.getEventById(eventId);}
}
