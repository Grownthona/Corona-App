package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Corona_Test extends AppCompatActivity {

    Button btn;

    int Selectphoto =1;
    EditText choose;
    Uri uri;
    ImageView imageview;
    EditText firstname,lastname,phone,email,gender,birth,adress;
    FirebaseDatabase database;
    DatabaseReference userref;
    String apptime,appdate,appbirth,appgender,fullname,enail,phonenum;

    FirebaseUser user;
    DatabaseReference reference;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_corona__test);

        choose = findViewById(R.id.phn);
        //imageview = findViewById(R.id.nidpic);
        firstname = findViewById(R.id.firstt);
        phone = findViewById(R.id.phn);
        email = findViewById(R.id.nid);
        btn = findViewById(R.id.gonext);

        enail = getIntent().getStringExtra("mail");
       // email.setText(enail);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Medical_Dashboard.class);
                startActivity(in);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
        // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;
                    firstname.setText(fullname );
                    phone.setText(mem.Phone);
                    email.setText(mem.Email);
                }
                else
                {
                    Toast.makeText(Corona_Test.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*database = FirebaseDatabase.getInstance();
        userref = database.getReference("Member");

        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(enail)){
                        firstname.setText(ds.child("fullName").getValue(String.class));
                        phone.setText(ds.child("phone").getValue(String.class));
                        fullname = ds.child("fullName").getValue(String.class);
                        phonenum = ds.child("phone").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstnamee = firstname.getText().toString().trim();
                String phonee = phone.getText().toString().trim();
                Intent in = new Intent(getApplicationContext(),Choose_Option.class);
                in.putExtra("fname",firstname.getText().toString());
                in.putExtra("phn",phone.getText().toString());
                in.putExtra("mail",enail);
                startActivity(in);
            }
        });
       // in.putExtra("keyname",email);
    }
}