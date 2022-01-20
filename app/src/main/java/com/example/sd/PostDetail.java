package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class PostDetail extends AppCompatActivity {

    EditText writecomment;
    ImageView sendcomment;
    DatabaseReference reff;
    String username,pid,ppost,userpic,commenterurl;
    TextView viewpost,poster;
    PostClass post,p2;
    FirebaseUser user;
    DatabaseReference reference,cmtsave;
    String userid;
    String fullname;
    commnterAdapter adapter;
    RecyclerView recview;
    ImageView img;
    long childcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        post = new PostClass();
        p2 = new PostClass();

        writecomment = findViewById(R.id.commentwrite);
        sendcomment = findViewById(R.id.imageView45);
        viewpost = findViewById(R.id.textView155);
        poster = findViewById(R.id.textView158);
        recview = findViewById(R.id.recyclerView5);


       username = getIntent().getStringExtra("posternme");
       pid = getIntent().getStringExtra("postid");
       ppost = getIntent().getStringExtra("posttxt");
       userpic = getIntent().getStringExtra("url");
       img = findViewById(R.id.imageView5566);
        Picasso.get().load(userpic).into(img);

        viewpost.setText(ppost);
        poster.setText(username);

        cmtsave = FirebaseDatabase.getInstance().getReference().child("Posts");

        reff = FirebaseDatabase.getInstance().getReference().child("POSTCOMMENTs").child(pid);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    childcount = (snapshot.getChildrenCount());

                    p2.setCommentnumber(String.valueOf(childcount));
                    p2.setPostername(username);
                    p2.setPostid(pid);
                    p2.setPostext(ppost);
                    p2.setUrl(userpic);
                    cmtsave.child(pid).setValue(p2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    fullname =  mem.FullName;
                    commenterurl= mem.url;
                }
                else
                {
                    Toast.makeText(PostDetail.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // shwdetail.setText(userid);

        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<PostClass> options =
                new FirebaseRecyclerOptions.Builder<PostClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("POSTCOMMENTs").child(pid), PostClass.class)
                        .build();
        adapter=new commnterAdapter(options);
        recview.setAdapter(adapter);



        sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random r = new Random();

                int id = r.nextInt(1000);
                post.setComment(writecomment.getText().toString());
                post.setCommenterurl(commenterurl);
                post.setCommenterid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                post.setCommentername(fullname);
                reff.child(String.valueOf(id)).setValue(post);
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