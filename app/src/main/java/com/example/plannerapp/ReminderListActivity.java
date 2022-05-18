package com.example.plannerapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.EventViewModel;
import com.example.plannerapp.model.Reminder;
import com.example.plannerapp.model.ReminderViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.UserViewModel;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {

    ReminderViewModel reminderViewModel;
    LinearLayout linearLayout;
    EventViewModel eventViewModel;
    TextView textView;
    TextView textViewDescription;
    TextView textViewDate;
    String username;
    String password;
    private List<Reminder> reminderList  = new ArrayList<>();
    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        linearLayout = findViewById(R.id.layout_list_reminders);

        Bundle bundle = getIntent().getExtras();
        Integer eventId = bundle.getInt("eventId");
        System.out.println("remindere");

        new Thread(new Runnable() {
            @Override
            public void run() {
                reminderViewModel = new ReminderViewModel(getApplication());
                eventViewModel = new EventViewModel(getApplication());
                event = eventViewModel.getEventById(eventId);
                reminderList = reminderViewModel.getAllRemindersForEvent(eventId);
                System.out.println("LISTA REMINDER " + reminderList.size());

                if(event!=null) {
                    if(reminderList.size()==0){
                        System.out.println("lista nula reminder");
                        textView = new TextView(getApplicationContext());
                        textView.setPadding(10, 10, 5, 5);
                        textView.setTextColor(Color.rgb(255, 255, 255));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(18);
                        textView.setText("YOU HAVE 0 REMINDERS");
                        textView.setBackgroundColor(Color.rgb(25, 55, 106));
                        linearLayout.addView(textView);

                    } else {
                        System.out.println("lista nenula reminder");
                        for(Reminder reminder: reminderList){
                            System.out.println("lista nula " + reminder.getIdReminder());
                            String reminderDescription = reminder.getDescription();
                            String reminderDate = reminder.getDate().toString();
                            runOnUiThread(() -> {
                                LinearLayout groupLinear = new LinearLayout(getApplicationContext());
                                groupLinear.setGravity(Gravity.CENTER);
                                groupLinear.setOrientation(LinearLayout.VERTICAL);
                                groupLinear.setPadding(40, 10, 40, 10);


                                textViewDescription = new TextView(getApplicationContext());
                                textViewDescription.setText(reminderDescription);
                                textViewDescription.setPadding(20, 10, 5, 5);
                                textViewDescription.setBackgroundColor(Color.parseColor("#d2f5ca"));



                                textViewDate = new TextView(getApplicationContext());
                                textViewDate.setText(reminderDate);
                                textViewDate.setPadding(20, 10, 5, 5);
                                textViewDate.setBackgroundColor(Color.parseColor("#d2f5ca"));

                                Button btnEdit = new Button(getApplicationContext());
                                btnEdit.setText("EDIT");
                                btnEdit.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnEdit.setOnClickListener(v->{
                                    Intent newIntent = new Intent (ReminderListActivity.this, EditReminderActivity.class);
                                    newIntent.putExtra("reminderId", reminder.getIdReminder());
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                Button btnDelete = new Button(getApplicationContext());
                                btnDelete.setText("DELETE");
                                btnDelete.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnDelete.setOnClickListener(v->{
                                    reminderViewModel.deleteReminder(reminder);
                                    Intent newIntent = new Intent (ReminderListActivity.this, WelcomeActivity.class);
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                TextView delimitator = new TextView(getApplicationContext());

                                groupLinear.addView(textViewDescription);
                                groupLinear.addView(textViewDate);
                                groupLinear.addView(btnEdit);
                                groupLinear.addView(btnDelete);
                                groupLinear.addView(delimitator);
                                linearLayout.addView(groupLinear);
                            });

                        }

                    }
                }

            }
        }).start();
    }

}
