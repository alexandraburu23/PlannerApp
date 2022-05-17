package com.example.plannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.model.Note;
import com.example.plannerapp.model.NoteViewModel;

public class EditNoteActivity extends AppCompatActivity {

    NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Bundle bundle = getIntent().getExtras();
        Integer noteId = bundle.getInt("noteId");
        String username = bundle.getString("username");
        EditText title = findViewById(R.id.title_edit);
        EditText content = findViewById(R.id.content_edit);
        Button btnSave = findViewById(R.id.btn_save);

        new Thread(() -> {
            noteViewModel = new NoteViewModel(this.getApplication());
            Note note = noteViewModel.getNoteById(noteId);
            System.out.println("nota "+noteId);
            this.runOnUiThread(() ->{
                if(note != null){
                    title.setText(note.getTitle());
                    content.setText(note.getContent());
                }
            });

            btnSave.setOnClickListener(v->{
                note.setTitle(title.getText().toString());
                note.setContent(content.getText().toString());
                noteViewModel.updateNote(note);
                Intent newIntent = new Intent (EditNoteActivity.this, WelcomeActivity.class);
                newIntent.putExtra("username", username);
                startActivity(newIntent);
            });

        }).start();

    }

}
