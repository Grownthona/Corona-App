package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    TextView booktest,test;
    String email,num;
    CardView medsection,conferencesection,prevention,statics,helppost;
    FirebaseDatabase database;
    DatabaseReference userref;
    ImageView nav;
    CircleImageView profile;
    signin n;

    FirebaseUser user;
    DatabaseReference eference,reff;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        nav = findViewById(R.id.imageView8);

       // email = getIntent().getStringExtra("mail");
        database = FirebaseDatabase.getInstance();
        userref = database.getReference("Member");

       // n.setNumber("+8801531548768");
        n= new signin();
        booktest = findViewById(R.id.textView1j2);
        medsection = findViewById(R.id.cardView);
        conferencesection = findViewById(R.id.cardView4);
        test = findViewById(R.id.search);
        prevention = findViewById(R.id.cardView3);
        statics = findViewById(R.id.cardView29);
        helppost = findViewById(R.id.lastone);

        profile = findViewById(R.id.imageView7);

        user = FirebaseAuth.getInstance().getCurrentUser();
        eference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        eference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;

                    Picasso.get().load(mem.url).into(profile);
                }
                else
                {
                    Toast.makeText(Dashboard.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        conferencesection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,DoctorList.class);
                startActivity(in);
            }
        });

        helppost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,Posts.class);
                startActivity(in);
            }
        });


        prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,CoronaPreventionDashboard.class);
                startActivity(in);
            }
        });

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,Navbar.class);
                startActivity(in);
            }
        });


        //userref = database.getReference("Corona_Test");
        /*fetch data code */

       /* userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){

                        //test.setText(ds.child("phone").getValue(String.class));

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        /*fetch data code */



        medsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,Medical_Dashboard.class);
                in.putExtra("mail",num);
                startActivity(in);
            }
        });
        booktest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Dashboard.this,Welcome_Test.class);
                in.putExtra("mail",email);
                startActivity(in);
            }
        });
    }
}