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

public class Posts extends AppCompatActivity {

    EditText postwriteee;
    Button btn;
    PostClass post;
    DatabaseReference reference,reff,addtoproductlist;
    FirebaseUser user;
    String fullname;
    String userid;
    RecyclerView recview;
    ImageView img;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        post = new PostClass();

        postwriteee = findViewById(R.id.postwrite);
        img = findViewById(R.id.imageView49);

        postwriteee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Posts.this,WritePost.class);
                startActivity(in);
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        recview = findViewById(R.id.postshow);
        // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    fullname =  mem.FullName;
                    Picasso.get().load(mem.url).into(img);
                }
                else
                {
                    Toast.makeText(Posts.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       /* reff = FirebaseDatabase.getInstance().getReference().child("POSTCOMMENTs");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    /*childcount = (snapshot.getChildrenCount());
                    childcount= childcount+1;*/

             /*   }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })*/

        
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostClass> options =
                new FirebaseRecyclerOptions.Builder<PostClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), PostClass.class)
                        .build();
        adapter=new PostAdapter(options);
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