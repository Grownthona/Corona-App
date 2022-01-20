package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCheckDoctorAppoinemnt extends AppCompatActivity {

    RecyclerView recview;
    AdminCheckAppoinmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_doctor_appoinemnt);

        recview = findViewById(R.id.apppointview);

        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Doctorappoinment> options =
                new FirebaseRecyclerOptions.Builder<Doctorappoinment>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AdminDocAppoinmentlist"),Doctorappoinment.class)
                        .build();
        adapter=new AdminCheckAppoinmentAdapter(options);
        recview.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // adapter.startListening();
        adapter.startListening();
        //adapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // adapter.stopListening();
        adapter.stopListening();
        //  adapter3.stopListening();
    }
}