package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorList extends AppCompatActivity {

    RecyclerView recview;
    DoctorlistAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recview = findViewById(R.id.doccycle);

        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<doctors> options =
                new FirebaseRecyclerOptions.Builder<doctors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Member").child("Doctors"),doctors.class)
                        .build();
        adapter=new DoctorlistAdapter(options);
        recview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}