package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Corona_Symptom05 extends AppCompatActivity {

    TextView yes,no,yesline,noline,r;
    ImageView next;
    int point=0;
    String prevpoint,pp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__symptom05);

        yes = findViewById(R.id.ybtn2);
        no = findViewById(R.id.nobtn2);
        yesline = findViewById(R.id.textView1012);
        noline = findViewById(R.id.textView1632);
        next = findViewById(R.id.nextp2);
        r= findViewById(R.id.textView163);

        prevpoint = getIntent().getStringExtra("point");
        int value = Integer.parseInt(prevpoint);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesline.setVisibility(View.VISIBLE);
                point++;
                int totalpoint = value+point;
                r.setText(String.valueOf(totalpoint));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalpoint = value+0;
                r.setText(String.valueOf(totalpoint));
                noline.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Symptom05.this,Corona_Symtop_Fimalresult.class);
                in.putExtra("point",r.getText().toString());
                startActivity(in);
            }
        });
    }
}