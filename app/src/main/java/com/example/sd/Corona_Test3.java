package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Corona_Test3 extends AppCompatActivity {

    String apptime,appdate,appbirth,appgender,fullname,enail,phonenum,cost;

    TextView pricee;
    Button btn,add,minimize;
    DatabaseReference reff;
    CoronatestBooking booking;
    int count = 3750;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__test3);

        pricee = findViewById(R.id.coronabookprice);
        add = findViewById(R.id.plus);
        minimize = findViewById(R.id.minus);

        appdate = getIntent().getStringExtra("datee");
        apptime = getIntent().getStringExtra("timee");
        appbirth = getIntent().getStringExtra("birth");
        appgender = getIntent().getStringExtra("gender");
        fullname = getIntent().getStringExtra("fname");
        phonenum = getIntent().getStringExtra("phn");
        enail = getIntent().getStringExtra("mail");


        btn = findViewById(R.id.confirm);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >=3750) {
                    count += 3750;
                    pricee.setText("" + count+" "+"Tk");
                }
            }
        });


        minimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >3750) {
                    count -= 3750;
                    pricee.setText("" + count+" "+"Tk");
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Test3.this,Corona_Test6.class);
                in.putExtra("mail",enail);
                in.putExtra("phn",phonenum);
                in.putExtra("fname",fullname);
                in.putExtra("datee",appdate);
                in.putExtra("timee",apptime);
                in.putExtra("birth",appbirth);
                in.putExtra("gender",appgender);
                in.putExtra("price",Integer.toString(count));
                startActivity(in);
            }
        });

    }
}