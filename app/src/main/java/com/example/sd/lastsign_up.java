package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class lastsign_up extends AppCompatActivity {

    Button bt;
    String phnnumber,prevmail,passpassword,passname;
    EditText password,name,adress,catagorytype;

    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lastsign_up);
        bt = findViewById(R.id.nextf);
        password = findViewById(R.id.kkkkk2);

        name = findViewById(R.id.kkkklllllk);
        adress = findViewById(R.id.kkkkkaddress);
        catagorytype = findViewById(R.id.kkkklllllk2);
        prevmail = getIntent().getStringExtra("email");
        phnnumber = getIntent().getStringExtra("phonenum");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(password.getText().toString().trim())){
                    password.setError("Password is required");
                    return;
                }
                if(password.getText().toString().length() < 6){
                    password.setError("Password must be more than 6 digits");
                    return;
                }
                Intent in = new Intent(lastsign_up.this,Final_Signup.class);
                passpassword = password.getText().toString().trim();
                passname = name.getText().toString().trim();
                in.putExtra("emaill",prevmail);
                in.putExtra("phonenumm",phnnumber);
                in.putExtra("passwordd",passpassword);
                in.putExtra("name",passname);
                in.putExtra("Address",adress.getText().toString());
                in.putExtra("Catagory",catagorytype.getText().toString());
                startActivity(in);
            }
        });
    }
}