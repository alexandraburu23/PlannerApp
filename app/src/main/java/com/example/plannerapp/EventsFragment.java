package com.example.plannerapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EventsFragment extends Fragment {
    View view;
    Button buttonPlus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
//        return inflater.inflate(R.layout.fragment_notes,container,false);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Event Fragment", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
