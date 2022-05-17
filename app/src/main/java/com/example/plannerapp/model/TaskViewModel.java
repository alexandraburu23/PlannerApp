package com.example.plannerapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plannerapp.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static TaskRepository repository;
    public final List<Task> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository =  new TaskRepository(application);
        allTasks = repository.getAllData();
    }

    public List<Task> getAllTasks(){
        return allTasks;
    }
    public void insertTask(Task task){
        repository.insertTask(task);
    }

    public void deleteTask(Task task){
        repository.deleteTask(task);
    }

    public void updateTask(Task task){repository.updateTask(task);}

    public List<Task> getAllTasksForUser(Integer userId) { return repository.getAllTasksForUser(userId);}

    public Task getTaskById(Integer noteId) { return repository.getTaskById(noteId);}
}
