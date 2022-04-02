package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.util.MyRoomDatabase;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;
    private final List<Task> allTasks;

    public TaskRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTask();
    }
    public List<Task> getAllData(){
        return allTasks;
    }
}
