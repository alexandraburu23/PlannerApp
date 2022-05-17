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

    public void insertUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> userDao.insertUser(user));
    }

    public void deleteAll(){
        MyRoomDatabase.databaseWriteExecutor.execute(()->userDao.deleteAll());
    }

    public void updateUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> userDao.updateUser(user));
    }

    public User getUserByUsername(String inputUsername){
        return userDao.getUserByUsername(inputUsername);
    }
    public User getUser(int id){
        return userDao.getUser(id);
    }

    public User getUserByUsernameAndPassword(String inputUsername, String inputPassword){
        return userDao.getUserByUsernameAndPassword(inputUsername, inputPassword);
    }

    public void registerUser(User user){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> userDao.registerUser(user));
    }

}
