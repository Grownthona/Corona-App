package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Navbar extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference eference,reff;
    String userid;

    //ImageView ;
    TextView logout;
    ImageView name,goprofile,goproductview,resetpass,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
       // img = findViewById(R.id.imageView62);
       // name = findViewById(R.id.textView113);
        goprofile = findViewById(R.id.imageView68);
        goproductview = findViewById(R.id.imageView70);
        logout = findViewById(R.id.textView203);
        resetpass = findViewById(R.id.imageView69);
        address = findViewById(R.id.imageView72);

      /*  user = FirebaseAuth.getInstance().getCurrentUser();
        eference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        eference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;

                    name.setText(mem.FullName);
                    Picasso.get().load(mem.url).into(img);
                }
                else
                {
                    Toast.makeText(Navbar.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        goprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Navbar.this,UserProfile.class);
                startActivity(in);
            }
        });

        goproductview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Navbar.this,UsershowTotalProducts.class);
                startActivity(in);
            }
        });

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Navbar.this,UpdatePassword.class);
                startActivity(in);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Navbar.this,Login.class);
                startActivity(in);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Navbar.this,UpdateAddress.class);
                startActivity(in);
            }
        });

    }
}