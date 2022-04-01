package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.util.MyRoomDatabase;

import java.util.List;

public class ReminderRepository {
    private final ReminderDao reminderDao;
    private final List<Reminder> allReminders;

    public ReminderRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        reminderDao = db.reminderDao();
        allReminders = reminderDao.getAllReminders();
    }
    public List<Reminder> getAllData(){
        return allReminders;
    }
}
