package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DoctorDetails extends AppCompatActivity {

    TextView name, spec, time, des;
    String dname ,dspec,dtime,docdes,docpic;
    ImageView url;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        des = findViewById(R.id.describee);
        name = findViewById(R.id.docnamee);
        spec = findViewById(R.id.doccatt);
        time = findViewById(R.id.timeee);
        url = findViewById(R.id.docpic2);
        btn = findViewById(R.id.button11);

        dname = getIntent().getStringExtra("name");
        dspec = getIntent().getStringExtra("sprcial");
        dtime = getIntent().getStringExtra("time");
        docdes = getIntent().getStringExtra("about");
        docpic = getIntent().getStringExtra("url");

        name.setText(dname);
        des.setText(docdes);
        spec.setText(dspec);
        time.setText(dtime);
        Picasso.get().load(docpic).into(url);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DoctorDetails.this,SelectDoctorAppoinment.class);
                in.putExtra("name",dname);
                in.putExtra("sprcial",dspec);
                in.putExtra("url",docpic);
                in.putExtra("about",docdes);
                in.putExtra("time",dtime);
                startActivity(in);
            }
        });

    }
}