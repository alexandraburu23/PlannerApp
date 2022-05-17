package com.example.plannerapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    Button btn_login, btn_register;
    TwitterLoginButton btn_login_twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btln_login);
        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("from", "MainActivity");
            startActivity(intent);
        });

        btn_register = findViewById(R.id.btln_register);
        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btn_login_twitter = (TwitterLoginButton) findViewById(R.id.login_button);
        btn_login_twitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                //String token = authToken.token;
                //  String secret = authToken.secret;
                loginMethod(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_LONG).show();
            }
        });
    }

        public void loginMethod(TwitterSession twitterSession){
            String userName=twitterSession.getUserName();
            Intent intent= new Intent(MainActivity.this,WelcomeActivity.class);

            intent.putExtra("username",userName);
            startActivity(intent);
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Pass the activity result to the login button.
            btn_login_twitter.onActivityResult(requestCode, resultCode, data);
        }
}
