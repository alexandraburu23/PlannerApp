package com.example.plannerapp.data;

import android.app.Application;

import com.example.plannerapp.model.User;
import com.example.plannerapp.util.MyRoomDatabase;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;
    private final List<User> allUsers;

    public UserRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }
    public List<User> getAllData(){
        return allUsers;
    }
}
