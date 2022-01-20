package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CoronaTest_Dashboard extends AppCompatActivity {

    Button btn;
    String email,num,commenterurl;
    EditText review;
    DatabaseReference reff;
    MyReviewClass rr;
    ImageView sendreview;
    RatingBar ratingBar;

    RecyclerView recview;
    ShowReview adapter;

    FirebaseUser user;
    DatabaseReference reference;
    String userid;
    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_test__dashboard);
        email = getIntent().getStringExtra("mail");
        review = findViewById(R.id.reviewbox);
        sendreview = findViewById(R.id.imageView35);

        recview = findViewById(R.id.recreview);
        ratingBar = findViewById(R.id.ratingBar);

        reff = FirebaseDatabase.getInstance().getReference().child("Reviews");
        rr= new MyReviewClass();


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    fullname =  mem.FullName;
                    //Picasso.get().load(mem.url).into(img);
                    commenterurl= mem.url;
                }
                else
                {
                    Toast.makeText(CoronaTest_Dashboard.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<MyReviewClass> options =
                new FirebaseRecyclerOptions.Builder<MyReviewClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reviews"), MyReviewClass.class)
                        .build();
        adapter=new ShowReview(options);
        recview.setAdapter(adapter);

        sendreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random r = new Random();

                int id = r.nextInt(1000);
                rr.setReview(review.getText().toString());
                rr.setReviewername(fullname);
                rr.setReviewerurl(commenterurl);
                rr.setRatings(String.valueOf(ratingBar.getRating()));
                reff.child(String.valueOf(id)).setValue(rr);
            }
        });

        btn = findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CoronaTest_Dashboard.this,Corona_Test.class);
                in.putExtra("mail",email);
                startActivity(in);
            }
        });
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