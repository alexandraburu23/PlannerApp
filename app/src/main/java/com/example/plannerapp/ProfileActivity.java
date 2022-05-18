package com.example.plannerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plannerapp.R;
import com.example.plannerapp.model.User;
import com.example.plannerapp.model.UserViewModel;

public class ProfileActivity extends AppCompatActivity{

    LinearLayout linearLayout;
    TextView textView;
    TextView textViewUsername;
    TextView textViewEmail;
    private ImageView imageView;
    private Button button, btnHome;
    String username;
    User user;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences preferences = this.getApplication().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        username = preferences.getString("username", "default value");
        imageView = findViewById(R.id.capturedImage);
        button = findViewById(R.id.openCamera);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 100);

            }
        });
        btnHome = findViewById(R.id.backHome);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent = new Intent(ProfileActivity.this, WelcomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        new Thread(() -> {
            userViewModel = new UserViewModel(this.getApplication());
            user = userViewModel.getUserByUsername(username);
            if(user != null){
                textViewUsername = findViewById(R.id.username);
                textViewEmail = findViewById(R.id.email);
                textViewUsername.setText("Username: " + username);
                textViewEmail.setText("Email: " + user.getEmail());


            }

        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(photo);
    }

}
