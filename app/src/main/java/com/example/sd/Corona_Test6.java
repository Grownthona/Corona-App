package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Corona_Test6 extends AppCompatActivity {

    String apptime,appdate,appbirth,appgender,fullname,enail,phonenum,cost;
    TextView namee,phonee,date,agee,timee,gen,addrss,priceeee;
    DatabaseReference reff;
    Button btn;
    Button btn2;
    CoronatestBooking book;
    FirebaseDatabase database;
    DatabaseReference userref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__test6);

        appdate = getIntent().getStringExtra("datee");
        apptime = getIntent().getStringExtra("timee");
        appbirth = getIntent().getStringExtra("birth");
        appgender = getIntent().getStringExtra("gender");
        fullname = getIntent().getStringExtra("fname");
        phonenum = getIntent().getStringExtra("phn");
        enail = getIntent().getStringExtra("mail");
        cost = getIntent().getStringExtra("price");

        btn = findViewById(R.id.button4);

        book =new CoronatestBooking();
        reff = FirebaseDatabase.getInstance().getReference().child("CoronaTest");

        namee = findViewById(R.id.name);
        phonee = findViewById(R.id.phone);
        date = findViewById(R.id.appoinmentdate);
        agee = findViewById(R.id.age);
        timee =findViewById(R.id.time);
        gen = findViewById(R.id.gender);
        addrss =  findViewById(R.id.thikana);
        priceeee = findViewById(R.id.price);

       namee.setText(fullname);
       phonee.setText(phonenum);
       date.setText(appdate);
       agee.setText(appbirth);
       timee.setText(apptime);
       gen.setText(appgender);

       priceeee.setText(cost+" "+"Tk");

        database = FirebaseDatabase.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Test6.this,Dashboard.class);
                startActivity(in);
            }
        });


       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               book.setFirstname(fullname);
               //book.setPrice(cost);
               book.setAddress(addrss.getText().toString());
               book.setBirth(agee.toString());
               book.setEmail(enail);
               book.setGender(appgender);
               book.setDate(appdate);
               reff.child(phonenum).setValue(book);
               Intent in = new Intent(Corona_Test6.this,CoronaTestSesh.class);
               startActivity(in);

           }
       });
    }
}