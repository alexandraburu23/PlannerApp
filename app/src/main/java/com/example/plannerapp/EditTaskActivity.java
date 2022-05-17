package com.example.plannerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.NoteViewModel;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditTaskActivity extends AppCompatActivity {

    TaskViewModel taskViewModel;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Bundle bundle = getIntent().getExtras();
        Integer taskId = bundle.getInt("taskId");
        String username = bundle.getString("username");
        EditText category = findViewById(R.id.category_edit);
        EditText description = findViewById(R.id.description_edit);
        EditText status = findViewById(R.id.status_edit);
        DatePicker deadline=(DatePicker)findViewById(R.id.deadline_edit);

        Button btnSave = findViewById(R.id.btn_save);

        new Thread(() -> {
            taskViewModel = new TaskViewModel(this.getApplication());
            Task task = taskViewModel.getTaskById(taskId);
            System.out.println("nota "+taskId);
            this.runOnUiThread(() ->{
                if(task != null){
                    category.setText(task.getCategory());
                    description.setText(task.getDescription());
                    status.setText(task.getStatus());
                    int day = task.getDeadline().getDayOfMonth();
                    int month = task.getDeadline().getMonthValue() - 1;
                    int year = task.getDeadline().getYear();

                    deadline.updateDate(year, month, day);
                }
            });

            btnSave.setOnClickListener(v->{
                task.setCategory(category.getText().toString());
                task.setDescription(description.getText().toString());
                task.setStatus(status.getText().toString());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                String dateText = (deadline.getDayOfMonth()+"-"+ (deadline.getMonth()+1)+"-"+deadline.getYear()).toString();
                LocalDate deadlineDate = LocalDate.parse(dateText, formatter);
                task.setDeadline(deadlineDate);

                taskViewModel.updateTask(task);
                Intent newIntent = new Intent (EditTaskActivity.this, WelcomeActivity.class);
                newIntent.putExtra("username", username);
                startActivity(newIntent);
            });

        }).start();

    }


}
