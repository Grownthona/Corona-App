package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Corona_Test4 extends AppCompatActivity {

    Button btn;
    DatePicker datePicker;

    RadioButton male,female;
    String apptime,appdate,gender;
    TextView shwdte,shwgen;

    String appbirth,appgender,fullname,enail,phonenum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_corona__test4);

        btn = findViewById(R.id.gonext);
        male= findViewById(R.id.radiomale);
        female= findViewById(R.id.radiofemale);

        datePicker= findViewById(R.id.datePicker1);

        appdate = getIntent().getStringExtra("date");
        apptime = getIntent().getStringExtra("time");
        enail = getIntent().getStringExtra("mail");
        phonenum = getIntent().getStringExtra("phn");
        fullname = getIntent().getStringExtra("fname");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                     gender = "Male";
                }
                else if(female.isChecked()){
                     gender = "Female";
                }
                if(!male.isChecked() && !female.isChecked())
                {
                    Toast.makeText(Corona_Test4.this, "Please select one Gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                 int day = datePicker.getDayOfMonth();
                 int  month = datePicker.getMonth()+1;
                 int year = datePicker.getYear();
                 String birthday = (Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year));

                Intent in = new Intent(Corona_Test4.this,Corona_Test3.class);
                in.putExtra("mail",enail);
                in.putExtra("phn",phonenum);
                in.putExtra("fname",fullname);
                in.putExtra("datee",appdate);
                in.putExtra("timee",apptime);
                in.putExtra("birth",birthday);
                in.putExtra("gender",gender);
                startActivity(in);
            }
        });
    }
}