package com.example.plannerapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plannerapp.data.EventRepository;
import com.example.plannerapp.data.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    public static ReminderRepository repository;
    public final List<Reminder> allReminders;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        repository =  new ReminderRepository(application);
        allReminders = repository.getAllData();
    }

    public List<Reminder> getAllReminders(){
        return allReminders;
    }
    public void insertReminder(Reminder reminder){
        repository.insertReminder(reminder);
    }

    public void deleteReminder(Reminder reminder){
        repository.deleteReminder(reminder);
    }

    public void updateReminder(Reminder reminder){repository.updateReminder(reminder);}

    public List<Reminder> getAllRemindersForUser(Integer userId) { return repository.getAllRemindersForUser(userId);}

    public Reminder getReminderById(Integer eventId) { return repository.getReminderById(eventId);}

    public List<Reminder> getAllRemindersForEvent(Integer eventId){
        return repository.getAllRemindersForEvent(eventId);
    }
}
