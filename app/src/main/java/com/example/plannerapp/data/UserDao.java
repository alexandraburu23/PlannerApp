package com.example.plannerapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plannerapp.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * from user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("Select * from user where idUser=:idUser")
    User getUser(int idUser);

    @Query("Select * from user where username=:usernameInput")
    User getUserByUsername(String usernameInput);

    @Query("Select * from user where username=:username and password=:password")
    User getUserByUsernameAndPassword(String username, String password);

    @Insert
    void registerUser(User user);
}
