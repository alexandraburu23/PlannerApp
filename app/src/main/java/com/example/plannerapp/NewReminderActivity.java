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
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.model.ReminderViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewReminderActivity extends AppCompatActivity {
    DatePicker date;
    Button save;
    EditText description;
    private ReminderViewModel reminderViewModel;
    private EventViewModel eventViewModel;
    private Event event;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);
        description = findViewById(R.id.description_edit);
        date=(DatePicker)findViewById(R.id.date_edit);
        save = findViewById(R.id.btn_save);
        save.setOnClickListener( v ->{
            String descriptionText = description.getText().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            String dateText = (date.getDayOfMonth()+"-"+ (date.getMonth()+1)+"-"+date.getYear()).toString();
            LocalDate dateDate = LocalDate.parse(dateText, formatter);

            if(descriptionText.isEmpty() ){
                Toast.makeText(getApplicationContext(),"Enter description for a new reminder",Toast.LENGTH_SHORT).show();
            }
            else
            {
                new Thread(() -> {
                    reminderViewModel = new ReminderViewModel(this.getApplication());
                    eventViewModel = new EventViewModel(this.getApplication());
                    SharedPreferences preferences = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    String username = preferences.getString("username", "default value");
                    String password = preferences.getString("password", "default pass");
                    System.out.println("New reminder "+ username);
                    System.out.println(username + " " + password);
                    Bundle bundle = getIntent().getExtras();
                    Integer eventId = bundle.getInt("eventId");
                    event = eventViewModel.getEventById(eventId);
                    Reminder reminder = new Reminder(descriptionText, dateDate);
                    reminder.setId_FkEvent(event.getIdEvent());
                    reminderViewModel.insertReminder(reminder);
                    runOnUiThread((() -> {
                        Toast.makeText(getApplicationContext(),"New Reminder Created" + reminder.getIdReminder(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewReminderActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }));

                }).start();
            }
        });
    }



}
