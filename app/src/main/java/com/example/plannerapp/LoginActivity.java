package com.example.plannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;


public class LoginActivity extends AppCompatActivity {

    Button register, login;
    EditText editUsername, editPassword;

    private UserViewModel userViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.username_input);
        editPassword = findViewById(R.id.password_input);
        login = findViewById(R.id.btnl_login);
        register = findViewById(R.id.btnl_register);
        login.setOnClickListener(v -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter both username and password for login",Toast.LENGTH_SHORT).show();
            }
            else
            {
                new Thread(() -> {
                    userViewModel = new UserViewModel(this.getApplication());
                    User user = userViewModel.getUserByUsernameAndPassword(username, password);
                    if(user == null)
                    {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show());
                    }
                    else
                    {
                        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        preferences.edit().putString("username", username).apply();
                        preferences.edit().putString("password", password).apply();
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                    }
                }).start();
            }
        });
        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

}
