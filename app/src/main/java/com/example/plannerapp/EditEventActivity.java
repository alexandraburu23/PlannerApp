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
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditEventActivity extends AppCompatActivity {
    EventViewModel eventViewModel;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Bundle bundle = getIntent().getExtras();
        Integer eventId = bundle.getInt("eventId");
        String username = bundle.getString("username");
        EditText title = findViewById(R.id.title_edit);
        EditText description = findViewById(R.id.description_edit);
        EditText location = findViewById(R.id.location_edit);
        DatePicker date=(DatePicker)findViewById(R.id.date_edit);

        Button btnSave = findViewById(R.id.btn_save);

        new Thread(() -> {
            eventViewModel = new EventViewModel(this.getApplication());
            Event event = eventViewModel.getEventById(eventId);
            System.out.println("nota "+eventId);
            this.runOnUiThread(() ->{
                if(event != null){
                    title.setText(event.getTitle());
                    description.setText(event.getDescription());
                    location.setText(event.getLocation());
                    int day = event.getDate().getDayOfMonth();
                    int month = event.getDate().getMonthValue() - 1;
                    int year = event.getDate().getYear();

                    date.updateDate(year, month, day);
                }
            });

            btnSave.setOnClickListener(v->{
                event.setTitle(title.getText().toString());
                event.setDescription(description.getText().toString());
                event.setLocation(location.getText().toString());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                String dateText = (date.getDayOfMonth()+"-"+ (date.getMonth()+1)+"-"+date.getYear()).toString();
                LocalDate dateDate = LocalDate.parse(dateText, formatter);
                event.setDate(dateDate);

                eventViewModel.updateEvent(event);
                Intent newIntent = new Intent (EditEventActivity.this, WelcomeActivity.class);
                newIntent.putExtra("username", username);
                startActivity(newIntent);
            });

        }).start();

    }
}
