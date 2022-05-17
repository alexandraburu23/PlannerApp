package com.example.plannerapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class WelcomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(username);
        System.out.println(" ");
        System.out.println(" ");
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NotesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_notes);
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(username);
        System.out.println(" ");
        System.out.println(" ");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        System.out.println("Welcome"+username);
        Bundle bundle2 = new Bundle();
        bundle.putString("username", username);
        switch(item.getItemId()){
            case R.id.nav_notes:
                Fragment notesFragment = new NotesFragment();
                notesFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        notesFragment).commit();
                break;
            case R.id.nav_tasks:
                TasksFragment tasksFragment = new TasksFragment();
                tasksFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        tasksFragment).commit();
                break;
            case R.id.nav_events:
                EventsFragment eventsFragment = new EventsFragment();
                eventsFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        eventsFragment).commit();
                break;
            case R.id.nav_reminders:
                ReminderFragment reminderFragment = new ReminderFragment();
                reminderFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        reminderFragment).commit();
                break;
            case R.id.profile:
                Toast.makeText(this, "My profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
