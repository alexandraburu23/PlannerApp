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

    public void insertTask(Task task){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> taskDao.insertTask(task));
    }
    public void updateTask(Task task){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> taskDao.updateTask(task));
    }
    public void deleteTask(Task task){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> taskDao.deleteTask(task));
    }
    public List<Task> getAllTasksForUser(Integer userId){
        return taskDao.getAllTasksForUser(userId);
    }

    public Task getTaskById(Integer taskId){
        return taskDao.getTaskById(taskId);
    }
}
