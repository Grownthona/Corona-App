package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_See_Prescriptions extends AppCompatActivity {

    RecyclerView reciew2;
    AdminSeePrescriptionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__see__prescriptions);

        reciew2 = findViewById(R.id.presrec);
        reciew2.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        FirebaseRecyclerOptions<Member> options2 =
                new FirebaseRecyclerOptions.Builder<Member>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Prescriptions"), Member.class)
                        .build();
        adapter=new AdminSeePrescriptionAdapter(options2);
        reciew2.setAdapter(adapter);
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