package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Corona_Symtom02 extends AppCompatActivity {

    TextView yes,no,yesline,noline,r;
    ImageView next;
    int point=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__symtom02);

        yes = findViewById(R.id.ybtn2);
        no = findViewById(R.id.nobtn2);
        yesline = findViewById(R.id.textView1012);
        noline = findViewById(R.id.textView1632);
        next = findViewById(R.id.nextp2);
        r = findViewById(R.id.textView164);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesline.setVisibility(View.VISIBLE);
                point=1;
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point =0;
                r.setText(String.valueOf(point));
                noline.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Symtom02.this,CoronaSymtomp_03.class);
                in.putExtra("point",String.valueOf(point));
                startActivity(in);
            }
        });

    }
}