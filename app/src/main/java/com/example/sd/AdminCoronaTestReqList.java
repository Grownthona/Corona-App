package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCoronaTestReqList extends AppCompatActivity {

    AdminCoronatestReqListAdapter adapter;
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_corona_test_req_list);

        recview= findViewById(R.id.recviewtest);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CoronatestBooking> options =
                new FirebaseRecyclerOptions.Builder<CoronatestBooking>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CoronaTest"), CoronatestBooking.class)
                        .build();
        adapter=new AdminCoronatestReqListAdapter(options);
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