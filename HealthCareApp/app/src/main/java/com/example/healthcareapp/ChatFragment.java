package com.example.healthcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Doctor> doctorList;
    private DoctorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.doctorRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        doctorList = new ArrayList<>();
        adapter = new DoctorAdapter(getContext(), doctorList);
        recyclerView.setAdapter(adapter);

        populateData();
    }

    private void populateData() {
        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);

        for (int i = 0; i < doctorNames.length; i++) {
            doctorList.add(new Doctor(doctorNames[i]));
        }

        adapter.notifyDataSetChanged();
    }
}