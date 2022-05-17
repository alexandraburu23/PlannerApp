package com.example.plannerapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.plannerapp.data.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public static UserRepository repository;
    public final List<User> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository =  new UserRepository(application);
        allUsers = repository.getAllData();
    }

    public List<User> getAllUsers(){
        return allUsers;
    }
    public void insert(User user){
        repository.insertUser(user);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void updateUser(User user){repository.updateUser(user);}

    public User getUser(String username){return repository.getUserByUsername(username);}

    public User getUserById(int id){
        return  repository.getUser(id);
    }

    public User getUserByUsername(String inputUsername){
        return repository.getUserByUsername(inputUsername);
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return repository.getUserByUsernameAndPassword(username, password);
    }

    public void registerUser(User user){
        repository.registerUser(user);
    }
}
