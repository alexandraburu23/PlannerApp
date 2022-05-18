package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.User;

import java.util.List;

@Dao
public interface EventDao {
    @Query("Select * from event")
    List<Event> getAllEvents();

    @Insert
    void insertEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("Select * from event where id_FkUser=:userId")
    List<Event> getAllEventsForUser(Integer userId);

    @Query("Select * from event where idEvent=:eventId")
    Event getEventById(Integer eventId);
}
