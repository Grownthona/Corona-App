package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Corona_Test5 extends AppCompatActivity {

    EditText mail,number,name;
    FirebaseDatabase database;
    DatabaseReference userref;
    String apptime,appdate,appbirth,appgender,fullname,enail,phonenum;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__test5);
        name = findViewById(R.id.firstt);
        number = findViewById(R.id.nphone);
        mail = findViewById(R.id.nemail);

        enail = "rahmangronthona@gmail.com";
        mail.setText(enail);

        appdate = getIntent().getStringExtra("datee");
        apptime = getIntent().getStringExtra("timee");
        appbirth = getIntent().getStringExtra("birth");
        appgender = getIntent().getStringExtra("gender");
        btn = findViewById(R.id.gonext5);

        database = FirebaseDatabase.getInstance();
        userref = database.getReference("Member");

        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals("rahmangronthona@gmail.com")){
                        name.setText(ds.child("fullName").getValue(String.class));
                        number.setText(ds.child("phone").getValue(String.class));
                        fullname = ds.child("fullName").getValue(String.class);
                        phonenum = ds.child("phone").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Test5.this,Corona_Test6.class);
                in.putExtra("dateee",appdate);
                in.putExtra("timeee",apptime);
                in.putExtra("birthh",appbirth);
                in.putExtra("genderr",appgender);
               in.putExtra("fullnme",name.getText().toString());
                in.putExtra("phn",number.getText().toString());
                startActivity(in);
            }
        });
    }
}