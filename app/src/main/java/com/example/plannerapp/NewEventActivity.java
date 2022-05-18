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

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.EventViewModel;
import com.example.plannerapp.model.NoteViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewEventActivity extends AppCompatActivity {
    Button save, set_reminder;
    EditText title, description, location;
    DatePicker date;
    private EventViewModel eventViewModel;
    private UserViewModel userViewModel;
    private User user;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        location = findViewById(R.id.location_edit);
        date=(DatePicker)findViewById(R.id.date_edit);
        save = findViewById(R.id.btn_save);

        save.setOnClickListener( v ->{
            String titleText = title.getText().toString();
            String descriptionText = description.getText().toString();
            String locationText = location.getText().toString();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            String dateText = (date.getDayOfMonth()+"-"+ (date.getMonth()+1)+"-"+date.getYear()).toString();
            LocalDate dateDate = LocalDate.parse(dateText, formatter);

            if(titleText.isEmpty() || descriptionText.isEmpty() || locationText.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter title, description and location for a new note",Toast.LENGTH_SHORT).show();
            } else {
                new Thread(() -> {
                    eventViewModel = new EventViewModel(this.getApplication());
                    userViewModel = new UserViewModel(this.getApplication());
                    SharedPreferences preferences = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    String username = preferences.getString("username", "default value");
                    String password = preferences.getString("password", "default pass");
                    System.out.println("New event "+ username);
                    System.out.println(username + " " + password);
                    user = userViewModel.getUserByUsernameAndPassword(username,password);

                    Event event = new Event(titleText, descriptionText, dateDate, locationText );
                    event.setId_FkUser(user.getIdUser());
                    eventViewModel.insertEvent(event);
                    runOnUiThread((() -> {
                        Toast.makeText(getApplicationContext(),"New Event Created" + event.getTitle(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewEventActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                    }));

                }).start();
            }
        });
    }
}
