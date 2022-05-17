package com.example.plannerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.NoteViewModel;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

public class NewNoteActivity extends AppCompatActivity {

    Button save;
    EditText title, content;
    private NoteViewModel noteViewModel;
    private UserViewModel userViewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);
        title = findViewById(R.id.title_edit);
        content = findViewById(R.id.content_edit);
        save = findViewById(R.id.btn_save);
        save.setOnClickListener( v ->{
            String titleText = title.getText().toString();
            String contentText = content.getText().toString();
            if(titleText.isEmpty() || contentText.isEmpty()){
                Toast.makeText(getApplicationContext(),"Enter both title and content for a new note",Toast.LENGTH_SHORT).show();
            }
            else
            {
                new Thread(() -> {
                    noteViewModel = new NoteViewModel(this.getApplication());
                    userViewModel = new UserViewModel(this.getApplication());
                    SharedPreferences preferences = getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    String username = preferences.getString("username", "default value");
                    String password = preferences.getString("password", "default pass");
                    System.out.println("New note "+ username);
                    System.out.println(username + " " + password);
                    user = userViewModel.getUserByUsernameAndPassword(username,password);
                    Note note = new Note(titleText, contentText);
                    note.setId_FkUser(user.getIdUser());
                    noteViewModel.insertNote(note);
                    runOnUiThread((() -> {
                        Toast.makeText(getApplicationContext(),"New Note Created" + note.getTitle(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewNoteActivity.this, WelcomeActivity.class);
                        intent.putExtra("username", user.getUsername());
                        startActivity(intent);
                    }));

                }).start();
            }
        });

    }

}
