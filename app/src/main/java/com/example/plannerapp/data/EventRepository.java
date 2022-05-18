package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.Note;
import com.example.plannerapp.util.MyRoomDatabase;

import java.util.List;

public class EventRepository {
    private final EventDao eventDao;
    private final List<Event> allEvents;

    public EventRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        eventDao = db.eventDao();
        allEvents = eventDao.getAllEvents();
    }
    public List<Event> getAllData(){
        return allEvents;
    }

    public void insertEvent(Event event){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> eventDao.insertEvent(event));
    }
    public void updateEvent(Event event){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> eventDao.updateEvent(event));
    }
    public void deleteEvent(Event event){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> eventDao.deleteEvent(event));
    }
    public List<Event> getAllEventsForUser(Integer userId){
        return eventDao.getAllEventsForUser(userId);
    }

    public Event getEventById(Integer noteId){
        return eventDao.getEventById(noteId);
    }
}
