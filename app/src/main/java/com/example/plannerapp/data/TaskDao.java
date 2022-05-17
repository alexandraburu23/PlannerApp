package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.User;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("Select * from task")
    List<Task> getAllTask();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("Select * from task where id_FkUser=:userId")
    List<Task> getAllTasksForUser(Integer userId);

    @Query("Select * from task where idTask=:taskId")
    Task getTaskById(Integer taskId);
}

