package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class Videolist extends AppCompatActivity {

    CardView one,two,three;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);


        one = findViewById(R.id.cardfront);
        two = findViewById(R.id.cardk);
        three = findViewById(R.id.cardk3);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Videolist.this,Mask_Video.class);
                startActivity(in);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Videolist.this,SecondVideo.class);
                startActivity(in);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Videolist.this,Second2Video.class);
                startActivity(in);
            }
        });
    }
}