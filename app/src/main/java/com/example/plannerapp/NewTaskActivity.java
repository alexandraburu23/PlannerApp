package com.example.plannerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.NoteViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewTaskActivity extends AppCompatActivity {
    DatePicker deadline;
    Button save;
    EditText category, description, status;
    private TaskViewModel taskViewModel;
    private UserViewModel userViewModel;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        category = findViewById(R.id.category_edit);
        description = findViewById(R.id.description_edit);
        status = findViewById(R.id.status_edit);
        deadline=(DatePicker)findViewById(R.id.deadline_edit);
        save = findViewById(R.id.btn_save);
        save.setOnClickListener( v ->{
            String categoryText = category.getText().toString();
            String descriptionText = description.getText().toString();
            String statusText = status.getText().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            String dateText = (deadline.getDayOfMonth()+"-"+ (deadline.getMonth()+1)+"-"+deadline.getYear()).toString();
            LocalDate deadlineDate = LocalDate.parse(dateText, formatter);

            if(categoryText.isEmpty() || descriptionText.isEmpty() || statusText.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter category, description and status for a new note",Toast.LENGTH_SHORT).show();
            }
            else
            {
                new Thread(() -> {
                    taskViewModel = new TaskViewModel(this.getApplication());
                    userViewModel = new UserViewModel(this.getApplication());
                    SharedPreferences preferences = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    String username = preferences.getString("username", "default value");
                    String password = preferences.getString("password", "default pass");
                    System.out.println("New task "+ username);
                    System.out.println(username + " " + password);
                    user = userViewModel.getUserByUsernameAndPassword(username,password);
                    Task task = new Task(descriptionText, statusText, categoryText, deadlineDate);
                    task.setId_FkUser(user.getIdUser());
                    taskViewModel.insertTask(task);
                    runOnUiThread((() -> {
                        Toast.makeText(getApplicationContext(),"New Task Created" + task.getCategory(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewTaskActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                    }));

                }).start();
            }
        });
    }


}
