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

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.NoteViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReminderFragment extends Fragment {
    View view;
    Button buttonPlus;
    LinearLayout linearLayout;
    UserViewModel userViewModel;
    TaskViewModel taskViewModel;
    TextView textView;
    TextView textViewCategory;
    TextView textViewDescription;
    TextView textViewStatus;
    TextView textViewDate;
    String username;
    String password;
    User user;
    private List<Task> taskList  = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reminders, container, false);
        buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
//        SharedPreferences preferences = getActivity().getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        username = preferences.getString("username", "default value");
//        password = preferences.getString("password", "default pass");
//        System.out.println("Current tasks "+ username);
//        System.out.println(username + " " + password);
//
//        Thread timer = new Thread() {
//            @Override
//            public void run() {
//                taskViewModel = new TaskViewModel(getActivity().getApplication());
//                userViewModel = new UserViewModel(getActivity().getApplication());
//                user = userViewModel.getUserByUsernameAndPassword(username,password);
//                taskList = taskViewModel.getAllTasksForUser((int) user.getIdUser());
//                System.out.println("LISTA " + taskList.size());
//
//                if(user!=null) {
//                    if(taskList.size()==0){
//                        textView = new TextView(getActivity().getApplicationContext());
//                        textView.setPadding(10, 10, 5, 5);
//                        textView.setTextColor(Color.rgb(255, 255, 255));
//                        textView.setGravity(Gravity.CENTER);
//                        textView.setTextSize(18);
//                        textView.setText("YOU HAVE 0 TASKS");
//                        textView.setBackgroundColor(Color.rgb(25, 55, 106));
//                        linearLayout.addView(textView);
//
//                    }else {
//
//                        for(Task task : taskList){
//                            String taskCategory = task.getCategory();
//                            String taskDescription = task.getDescription();
//                            String taskStatus = task.getStatus();
//                            String taskDate = task.getDeadline().toString();
//                            getActivity().runOnUiThread(() -> {
//                                LinearLayout groupLinear = new LinearLayout(getActivity().getApplicationContext());
//                                groupLinear.setGravity(Gravity.CENTER);
//                                groupLinear.setOrientation(LinearLayout.VERTICAL);
//                                groupLinear.setPadding(40, 10, 40, 10);
//                                textViewCategory = new TextView(getActivity().getApplicationContext());
//                                textViewCategory.setText(taskCategory);
//                                textViewCategory.setPadding(10, 15, 5, 15);
//                                textViewCategory.setGravity(Gravity.CENTER);
//                                textViewCategory.setBackgroundColor(Color.parseColor("#0099cc"));
//                                textViewCategory.setTextColor(Color.WHITE);
//
//                                textViewDescription = new TextView(getActivity().getApplicationContext());
//                                textViewDescription.setText(taskDescription);
//                                textViewDescription.setPadding(20, 10, 5, 5);
//                                textViewDescription.setBackgroundColor(Color.parseColor("#d2f5ca"));
//
//                                textViewStatus = new TextView(getActivity().getApplicationContext());
//                                textViewStatus.setText(taskStatus);
//                                textViewStatus.setPadding(20, 10, 5, 5);
//                                textViewStatus.setBackgroundColor(Color.parseColor("#d2f5ca"));
//
//                                textViewDate = new TextView(getActivity().getApplicationContext());
//                                textViewDate.setText(taskDate);
//                                textViewDate.setPadding(20, 10, 5, 5);
//                                textViewDate.setBackgroundColor(Color.parseColor("#d2f5ca"));
//
//                                Button btnEdit = new Button(getActivity().getApplicationContext());
//                                btnEdit.setText("EDIT");
//                                btnEdit.setBackgroundColor(Color.parseColor("#ff0099cc"));
//
//                                btnEdit.setOnClickListener(v->{
//                                    Intent newIntent = new Intent (getActivity(), EditTaskActivity.class);
//                                    newIntent.putExtra("noteId", task.getIdTask());
//                                    newIntent.putExtra("username", username);
//                                    startActivity(newIntent);
//                                });
//
//                                Button btnDelete = new Button(getActivity().getApplicationContext());
//                                btnDelete.setText("DELETE");
//                                btnDelete.setBackgroundColor(Color.parseColor("#ff0099cc"));
//
//                                btnDelete.setOnClickListener(v->{
//                                    taskViewModel.deleteTask(task);
//                                    Intent newIntent = new Intent (getActivity(), WelcomeActivity.class);
//                                    newIntent.putExtra("username", username);
//                                    startActivity(newIntent);
//                                });
//
//                                TextView delimitator = new TextView(getActivity().getApplicationContext());
//
//                                groupLinear.addView(textViewCategory);
//                                groupLinear.addView(textViewDescription);
//                                groupLinear.addView(textViewStatus);
//                                groupLinear.addView(textViewDate);
//                                groupLinear.addView(btnEdit);
//                                groupLinear.addView(btnDelete);
//                                groupLinear.addView(delimitator);
//                                linearLayout.addView(groupLinear);
//                            });
//
//                        }
//
//                    }
//                }
//
//            }
//        };
//        timer.start();
//

//        return inflater.inflate(R.layout.fragment_notes,container,false);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Reminder Fragment", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
