package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Dashboard extends AppCompatActivity {

    Button addproduct,viewproduct,testview,appoinmentview,seeprescription,productlistt,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dashboard);

        addproduct = findViewById(R.id.addp);
        testview = findViewById(R.id.button22);
        viewproduct = findViewById(R.id.button21);
        appoinmentview = findViewById(R.id.button19);
        seeprescription = findViewById(R.id.button25);
        productlistt = findViewById(R.id.button23);
        logout = findViewById(R.id.button26);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this,Admin_Addproduct.class);
                startActivity(in);
            }
        });

        viewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this,UserProductRequest.class);
                startActivity(in);
            }
        });

        testview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this,AdminCoronaTestReqList.class);
                startActivity(in);
            }
        });
        appoinmentview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this, AdminCheckDoctorAppoinemnt.class);
                startActivity(in);
            }
        });
        seeprescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this, Admin_See_Prescriptions.class);
                startActivity(in);
            }
        });

        productlistt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this, Admin_See_Productlist.class);
                startActivity(in);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Dashboard.this,Login.class);
                startActivity(in);
            }
        });
    }
}