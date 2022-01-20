package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CoronaPreventionDashboard extends AppCompatActivity {
    ImageView next;
    CardView prventionvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_prevention_dashboard);
        next = findViewById(R.id.imageView52);
        prventionvideo = findViewById(R.id.cardk);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CoronaPreventionDashboard.this,Corona_Symptom01.class);
                startActivity(in);
            }
        });

        prventionvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CoronaPreventionDashboard.this,Videolist.class);
                startActivity(in);
            }
        });

    }
}