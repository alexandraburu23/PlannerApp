package com.example.plannerapp;


import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.plannerapp.model.Event;
import com.example.plannerapp.model.EventViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {
    View view;
    Button buttonPlus;
    LinearLayout linearLayout;
    UserViewModel userViewModel;
    EventViewModel eventViewModel;
    TextView textView;
    TextView textViewTitle;
    TextView textViewDescription;
    TextView textViewLocation;
    TextView textViewDate;
    String username;
    String password;
    User user;
    private List<Event> eventList  = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_list_events);

//        return inflater.inflate(R.layout.fragment_notes,container,false);
        SharedPreferences preferences = getActivity().getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = preferences.getString("username", "default value");
        password = preferences.getString("password", "default pass");
        System.out.println("Current tasks "+ username);
        System.out.println(username + " " + password);

        Thread timer = new Thread() {
            @Override
            public void run() {
                eventViewModel = new EventViewModel(getActivity().getApplication());
                userViewModel = new UserViewModel(getActivity().getApplication());
                user = userViewModel.getUserByUsernameAndPassword(username,password);
                eventList = eventViewModel.getAllEventsForUser((int) user.getIdUser());
                System.out.println("LISTA " + eventList.size());

                if(user!=null) {
                    if(eventList.size()==0){
                        textView = new TextView(getActivity().getApplicationContext());
                        textView.setPadding(10, 10, 5, 5);
                        textView.setTextColor(Color.rgb(255, 255, 255));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(18);
                        textView.setText("YOU HAVE 0 EVENTS");
                        textView.setBackgroundColor(Color.rgb(25, 55, 106));
                        linearLayout.addView(textView);

                    }else {

                        for(Event event : eventList){
                            String eventTitle = event.getTitle();
                            String eventDescription = event.getDescription();
                            String eventLocation = event.getLocation();
                            String eventDate = event.getDate().toString();
                            getActivity().runOnUiThread(() -> {
                                LinearLayout groupLinear = new LinearLayout(getActivity().getApplicationContext());
                                groupLinear.setGravity(Gravity.CENTER);
                                groupLinear.setOrientation(LinearLayout.VERTICAL);
                                groupLinear.setPadding(40, 10, 40, 10);
                                textViewTitle = new TextView(getActivity().getApplicationContext());
                                textViewTitle.setText(eventTitle);
                                textViewTitle.setPadding(10, 15, 5, 15);
                                textViewTitle.setGravity(Gravity.CENTER);
                                textViewTitle.setBackgroundColor(Color.parseColor("#0099cc"));
                                textViewTitle.setTextColor(Color.WHITE);

                                textViewDescription = new TextView(getActivity().getApplicationContext());
                                textViewDescription.setText(eventDescription);
                                textViewDescription.setPadding(20, 10, 5, 5);
                                textViewDescription.setBackgroundColor(Color.parseColor("#d2f5ca"));

                                textViewLocation = new TextView(getActivity().getApplicationContext());
                                textViewLocation.setText(eventLocation);
                                textViewLocation.setPadding(20, 10, 5, 5);
                                textViewLocation.setBackgroundColor(Color.parseColor("#d2f5ca"));

                                textViewDate = new TextView(getActivity().getApplicationContext());
                                textViewDate.setText(eventDate);
                                textViewDate.setPadding(20, 10, 5, 5);
                                textViewDate.setBackgroundColor(Color.parseColor("#d2f5ca"));

                                Button btnEdit = new Button(getActivity().getApplicationContext());
                                btnEdit.setText("EDIT");
                                btnEdit.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnEdit.setOnClickListener(v->{
                                    Intent newIntent = new Intent (getActivity(), EditEventActivity.class);
                                    newIntent.putExtra("eventId", event.getIdEvent());
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                Button btnDelete = new Button(getActivity().getApplicationContext());
                                btnDelete.setText("DELETE");
                                btnDelete.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnDelete.setOnClickListener(v->{
                                    eventViewModel.deleteEvent(event);
                                    Intent newIntent = new Intent (getActivity(), WelcomeActivity.class);
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                Button btnSetReminder = new Button(getActivity().getApplicationContext());
                                btnSetReminder.setText("SET NEW REMINDER");
                                btnSetReminder.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnSetReminder.setOnClickListener(v -> {
                                    Intent newIntent = new Intent (getActivity(), NewReminderActivity.class);
                                    newIntent.putExtra("eventId", event.getIdEvent());
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                Button btnListReminder = new Button(getActivity().getApplicationContext());
                                btnListReminder.setText("SEE REMINDERS");
                                btnListReminder.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnListReminder.setOnClickListener(v -> {
                                    Intent newIntent = new Intent (getActivity(), ReminderListActivity.class);
                                    newIntent.putExtra("eventId", event.getIdEvent());
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                TextView delimitator = new TextView(getActivity().getApplicationContext());

                                groupLinear.addView(textViewTitle);
                                groupLinear.addView(textViewDescription);
                                groupLinear.addView(textViewLocation);
                                groupLinear.addView(textViewDate);
                                groupLinear.addView(btnEdit);
                                groupLinear.addView(btnDelete);
                                groupLinear.addView(btnSetReminder);
                                groupLinear.addView(btnListReminder);
                                groupLinear.addView(delimitator);
                                linearLayout.addView(groupLinear);
                            });

                        }

                    }
                }

            }
        };
        timer.start();

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Event Fragment", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), NewEventActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
