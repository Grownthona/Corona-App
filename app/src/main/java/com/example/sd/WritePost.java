package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class WritePost extends AppCompatActivity {

    PostClass post;
    EditText takepost;
    Button btn;

    TextView username;
    DatabaseReference reference,reff,addtoproductlist;
    ImageView img;
    FirebaseUser user;
    String fullname,imgurl;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        takepost = findViewById(R.id.write);
        username = findViewById(R.id.textView170);
        btn = findViewById(R.id.button16);
         img = findViewById(R.id.imageView54);

        post = new PostClass();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
        // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    fullname =  mem.FullName;
                    username.setText(fullname);
                    imgurl = mem.url;
                    Picasso.get().load(mem.url).into(img);
                }
                else
                {
                    Toast.makeText(WritePost.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random r = new Random();

                int id = r.nextInt(1000);


                reff = FirebaseDatabase.getInstance().getReference().child("Posts");
                post.setPostext(takepost.getText().toString());
                post.setUserid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                post.setUrl(imgurl);
                post.setPostername(fullname);
                post.setPostid(String.valueOf(id));

                reff.child(String.valueOf(id)).setValue(post);
            }
        });
    }
}