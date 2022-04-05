package com.example.plannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    Button login;
    EditText editUsername, editEmail, editPassword, editCPassword;

    private UserViewModel userViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        editUsername = findViewById(R.id.username_edit);
        editEmail = findViewById(R.id.email_edit);
        editPassword = findViewById(R.id.password_edit);
        editCPassword = findViewById(R.id.confirm_password_edit);
        login = findViewById(R.id.btn_edit);

        login.setOnClickListener(v -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            String confirmPassword = editCPassword.getText().toString();
            createPerson(password, confirmPassword, username);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        //login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private Boolean validateInput(User user, String confirmPassword) {
        return !user.getUsername().isEmpty() &&
                !user.getPassword().isEmpty() &&
                !confirmPassword.equals("");
    }

    private void createPerson(String password, String confirmPassword, String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(!validateInput(user, confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(confirmPassword)){
            Toast.makeText(getApplicationContext(), "Password and confirm password must be the same!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            new Thread(() -> {
                userViewModel = new UserViewModel(this.getApplication());
                User existingUsername = userViewModel.getUserByUsername(username);
                new Thread(() -> {
                    if(existingUsername != null){
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Username already used!", Toast.LENGTH_SHORT).show());
                    }
                    else{
                        userViewModel.registerUser(user);
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                            Intent intent;
                            intent = new Intent(RegisterActivity.this, ProfileUserActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        });
                    }
                }).start();
            }).start();
        }
    }

}
