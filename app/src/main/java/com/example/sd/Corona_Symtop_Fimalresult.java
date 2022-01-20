package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class Corona_Symtop_Fimalresult extends AppCompatActivity {

    String prevpoint;
    TextView result;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__symtop__fimalresult);

        result = findViewById(R.id.textView107);
        btn = findViewById(R.id.button10);
        prevpoint = getIntent().getStringExtra("point");
        int value = Integer.parseInt(prevpoint);

        //result.setText(String.valueOf(value));

        if(value<=1){
            result.setText("You don't have any COVID Symptoms");
        }else{
            result.setText("Sorry! You've some COVID Symptoms");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Corona_Symtop_Fimalresult.this,CoronaPreventionDashboard.class);

                startActivity(in);
            }
        });
    }
}