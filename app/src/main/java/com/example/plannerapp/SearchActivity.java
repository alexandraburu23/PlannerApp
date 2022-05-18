package com.example.plannerapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plannerapp.TaskAdapter;
import com.example.plannerapp.model.Task;
import com.example.plannerapp.model.TaskViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    // creating variables for
    // our ui components.
    private RecyclerView taskRV;

    TaskViewModel taskViewModel;

    // variable for our adapter
    // class and array list
    private TaskAdapter adapter;
    private ArrayList<Task> taskArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // initializing our variables.
        taskRV = findViewById(R.id.idSearch);

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Task> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Task task : taskArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (task.getCategory().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(task);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        taskArrayList = new ArrayList<>();

//        // below line is to add data to our array list.
//        taskArrayList.add(new CourseModal("DSA", "DSA Self Paced Course"));
//        taskArrayList.add(new CourseModal("JAVA", "JAVA Self Paced Course"));
//        taskArrayList.add(new CourseModal("C++", "C++ Self Paced Course"));
//        taskArrayList.add(new CourseModal("Python", "Python Self Paced Course"));
//        taskArrayList.add(new CourseModal("Fork CPP", "Fork CPP Self Paced Course"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskViewModel = new TaskViewModel(getApplication());
                taskArrayList = (ArrayList<Task>) taskViewModel.getAllTasks();


            }
        }).start();
        // initializing our adapter class.
        adapter = new TaskAdapter(taskArrayList, SearchActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        taskRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        taskRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        taskRV.setAdapter(adapter);
    }
}
