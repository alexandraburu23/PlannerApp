package com.example.plannerapp;


import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
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
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    View view;
    Button buttonPlus;
    LinearLayout linearLayout;
    UserViewModel userViewModel;
    NoteViewModel noteViewModel;
    TextView textView;
    TextView textViewTitle;
    TextView textViewContent;
    String username;
    String password;
    User user;
    private List<Note> noteList  = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notes, container, false);
        buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_list_notes);


        SharedPreferences preferences = getActivity().getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = preferences.getString("username", "default value");
        password = preferences.getString("password", "default pass");
        System.out.println("Current notes "+ username);
        System.out.println(username + " " + password);

//        new MyTask().execute();

        System.out.println(Thread.currentThread().getName());

        Thread timer = new Thread() {
            @Override
            public void run() {
                noteViewModel = new NoteViewModel(getActivity().getApplication());
                userViewModel = new UserViewModel(getActivity().getApplication());
                user = userViewModel.getUserByUsernameAndPassword(username,password);
                noteList = noteViewModel.getAllNotesForUser((int) user.getIdUser());
                System.out.println("LISTA " + noteList.size());

                if(user!=null) {
                    if(noteList.size()==0){
                        textView = new TextView(getActivity().getApplicationContext());
                        textView.setPadding(10, 10, 5, 5);
                        textView.setTextColor(Color.rgb(255, 255, 255));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(18);
                        textView.setText("YOU HAVE 0 NOTES");
                        textView.setBackgroundColor(Color.rgb(25, 55, 106));
                        linearLayout.addView(textView);

                    }else {

                        for(Note note : noteList){
                            String noteTitle = note.getTitle();
                            String noteContent = note.getContent();
                            getActivity().runOnUiThread(() -> {
                                LinearLayout groupLinear = new LinearLayout(getActivity().getApplicationContext());
                                groupLinear.setGravity(Gravity.CENTER);
                                groupLinear.setOrientation(LinearLayout.VERTICAL);
                                groupLinear.setPadding(40, 10, 40, 10);
                                textViewTitle = new TextView(getActivity().getApplicationContext());
                                textViewTitle.setText(noteTitle);
                                textViewTitle.setPadding(10, 15, 5, 15);
                                textViewTitle.setGravity(Gravity.CENTER);
                                textViewTitle.setBackgroundColor(Color.parseColor("#0099cc"));
                                textViewTitle.setTextColor(Color.WHITE);

                                textViewContent = new TextView(getActivity().getApplicationContext());
                                textViewContent.setText(noteContent);
                                textViewContent.setPadding(20, 10, 5, 5);
                                textViewContent.setBackgroundColor(Color.parseColor("#d2f5ca"));

                                Button btnEdit = new Button(getActivity().getApplicationContext());
                                btnEdit.setText("EDIT");
                                btnEdit.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnEdit.setOnClickListener(v->{
                                    Intent newIntent = new Intent (getActivity(), EditNoteActivity.class);
                                    newIntent.putExtra("noteId", note.getIdNote());
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                Button btnDelete = new Button(getActivity().getApplicationContext());
                                btnDelete.setText("DELETE");
                                btnDelete.setBackgroundColor(Color.parseColor("#ff0099cc"));

                                btnDelete.setOnClickListener(v->{
                                    noteViewModel.deleteNote(note);
                                    Intent newIntent = new Intent (getActivity(), WelcomeActivity.class);
                                    newIntent.putExtra("username", username);
                                    startActivity(newIntent);
                                });

                                TextView delimitator = new TextView(getActivity().getApplicationContext());

                                groupLinear.addView(textViewTitle);
                                groupLinear.addView(textViewContent);
                                groupLinear.addView(btnEdit);
                                groupLinear.addView(btnDelete);
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
                Toast.makeText(getActivity(), "Notes Fragment", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), NewNoteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            new Thread(() -> {
                noteViewModel = new NoteViewModel(getActivity().getApplication());
                userViewModel = new UserViewModel(getActivity().getApplication());
                user = userViewModel.getUserByUsernameAndPassword(username,password);
                noteList = noteViewModel.getAllNotesForUser((int) user.getIdUser());
                System.out.println("LISTA " + noteList.size());

                if(user!=null) {
                    if(noteList.size()==0){
                        textView = new TextView(getActivity().getApplicationContext());
                        textView.setPadding(10, 10, 5, 5);
                        textView.setTextColor(Color.rgb(255, 255, 255));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(18);
                        textView.setText("YOU HAVE 0 NOTES");
                        textView.setBackgroundColor(Color.rgb(25, 55, 106));
                        linearLayout.addView(textView);

                    }else {
                        LinearLayout groupLinear = new LinearLayout(getActivity().getApplicationContext());
                        groupLinear.setGravity(Gravity.CENTER);
                        groupLinear.setOrientation(LinearLayout.VERTICAL);
                        groupLinear.setPadding(20, 10, 20, 10);
                        for(Note note : noteList){
                            String noteTitle = note.getTitle();
                            String noteContent = note.getContent();
                            getActivity().runOnUiThread(() -> {
                                textViewTitle = new TextView(getActivity().getApplicationContext());
                                textViewTitle.setText(noteTitle);
                                textViewTitle.setPadding(10, 10, 5, 5);
                                textView.setBackgroundColor(Color.parseColor("@android:color/holo_blue_dark"));
                                textViewContent = new TextView(getActivity().getApplicationContext());
                                textViewContent.setText(noteContent);
                                textViewContent.setPadding(10, 10, 5, 5);
                                textView.setBackgroundColor(Color.parseColor("@android:color/green"));
                                linearLayout.addView(textViewTitle);
                                linearLayout.addView(textViewContent);
                            });

                        }

                    }
                }


            });

            return null;
        }


    }


}
