package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.Event;
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
}
