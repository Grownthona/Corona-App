package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PrescriptionDetail extends AppCompatActivity {

    String name,address,phone,pic;
    TextView username,addressst,phonenumber;
    ImageView medpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_detail);
        username = findViewById(R.id.textView223);
        name = getIntent().getStringExtra("name");
        username.setText(name);

        addressst = findViewById(R.id.textView229);
        address = getIntent().getStringExtra("address");
        addressst.setText(address);

        phonenumber = findViewById(R.id.textView225);
        phone = getIntent().getStringExtra("phn");
        phonenumber.setText(phone);

        medpic = findViewById(R.id.imageView75);
        pic = getIntent().getStringExtra("url");
        Picasso.get().load(pic).into(medpic);
    }
}