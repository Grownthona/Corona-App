package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminCode extends AppCompatActivity {

    EditText admincode;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_code);

        admincode = findViewById(R.id.code);
        btn = findViewById(R.id.button28);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admincode.getText().equals("2135")){
                    Intent in = new Intent(AdminCode.this,Admin_Dashboard.class);
                    startActivity(in);
                }
            }
        });

    }
}