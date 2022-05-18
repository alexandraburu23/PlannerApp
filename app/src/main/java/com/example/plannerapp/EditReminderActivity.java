package com.example.plannerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.EventViewModel;
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.model.ReminderViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditReminderActivity extends AppCompatActivity {
    ReminderViewModel reminderViewModel;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);
        Bundle bundle = getIntent().getExtras();
        Integer reminderId = bundle.getInt("reminderId");
        String username = bundle.getString("username");
        EditText description = findViewById(R.id.description_edit);
        DatePicker date=(DatePicker)findViewById(R.id.date_edit);

        Button btnSave = findViewById(R.id.btn_save);

        new Thread(new Runnable() {
            @Override
            public void run() {
                reminderViewModel = new ReminderViewModel(getApplication());
                Reminder reminder = reminderViewModel.getReminderById(reminderId);
                System.out.println("reminder "+reminderId);
                runOnUiThread(() ->{
                    if(reminder != null){
                        description.setText(reminder.getDescription());
                        int day = reminder.getDate().getDayOfMonth();
                        int month = reminder.getDate().getMonthValue() - 1;
                        int year = reminder.getDate().getYear();

                        date.updateDate(year, month, day);
                    }
                });

                btnSave.setOnClickListener(v->{
                    reminder.setDescription(description.getText().toString());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                    String dateText = (date.getDayOfMonth()+"-"+ (date.getMonth()+1)+"-"+date.getYear()).toString();
                    LocalDate dateDate = LocalDate.parse(dateText, formatter);
                    reminder.setDate(dateDate);

                    reminderViewModel.updateReminder(reminder);
                    Intent newIntent = new Intent (EditReminderActivity.this, WelcomeActivity.class);

                    newIntent.putExtra("username", username);
                    startActivity(newIntent);
                });
            }


        }).start();

    }
}
