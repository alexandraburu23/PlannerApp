package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {
    @Query("Select * from reminder")
    List<Reminder> getAllReminders();

    @Insert
    void insertReminder(Reminder reminder);

    @Update
    void updateReminder(Reminder reminder);

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("Select r.idReminder, r.description, r.date, r.id_FkEvent from reminder r, event e where r.id_FkEvent = e.idEvent and e.id_FkUser=:userId")
    List<Reminder> getAllRemindersForUser(Integer userId);

    @Query("Select * from reminder where idReminder=:reminderId")
    Reminder getReminderById(Integer reminderId);

    @Query("Select * from reminder where id_FkEvent=:eventId")
    List<Reminder> getAllRemindersForEvent(Integer eventId);
}
