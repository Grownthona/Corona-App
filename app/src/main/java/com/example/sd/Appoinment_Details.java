package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Appoinment_Details extends AppCompatActivity {

    TextView patientname,doctorname,pemail,pphone,pfees,pdate;
    String ppname,docname,mail,phone,fees,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment__details);

        ppname = getIntent().getStringExtra("pname");
        patientname = findViewById(R.id.textView227);
        patientname.setText(ppname);

        docname = getIntent().getStringExtra("docname");
        doctorname = findViewById(R.id.textView231);
        doctorname.setText(docname);

        mail = getIntent().getStringExtra("pmail");
        pemail = findViewById(R.id.textView236);
        pemail.setText(mail);

        phone = getIntent().getStringExtra("pphone");
        pphone = findViewById(R.id.textView233);
        pphone.setText(phone);

        fees = getIntent().getStringExtra("pfees");
        pfees = findViewById(R.id.textView240);
        pfees.setText(fees);

        date = getIntent().getStringExtra("pdate");
        pdate = findViewById(R.id.textView240);
        pdate.setText(date);
    }
}