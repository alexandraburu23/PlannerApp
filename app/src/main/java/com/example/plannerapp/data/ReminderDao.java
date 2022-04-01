package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plannerapp.model.Event;
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
}
