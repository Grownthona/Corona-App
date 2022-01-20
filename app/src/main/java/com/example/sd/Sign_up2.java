package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Sign_up2 extends AppCompatActivity {

    Button bh;
    CountryCodePicker ccp;
    EditText number;
    String phnnumber,prevmail;
    DatabaseReference rf,kl,ll;
    ProgressBar progressBar;
    Dialog dialog;
    Query checkuser;
    int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2);
        prevmail = getIntent().getStringExtra("keyname");
        bh = findViewById(R.id.next2);
        ccp = findViewById(R.id.codepicker);
        number = findViewById(R.id.kkkkkaddress);
        progressBar = findViewById(R.id.progressBar3);

        dialog = new Dialog(Sign_up2.this);
        dialog.setContentView(R.layout.back_signin);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.nextf);

        TextView shownam = dialog.findViewById(R.id.fillname);

       /* bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phnnumber = "+"+ccp.getFullNumber()+number.getText().toString().trim();
                Intent in = new Intent(getApplicationContext(),lastsign_up.class);
                in.putExtra("email",prevmail);
                in.putExtra("phonenum",phnnumber);
                startActivity(in);
            }
        });*/

        bh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phnnumber = "+"+ccp.getFullNumber()+number.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                kl = FirebaseDatabase.getInstance().getReference().child("Member");
                rf = FirebaseDatabase.getInstance().getReference().child("Member");
                rf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.hasChild(phnnumber)) {
                            Intent in = new Intent(getApplicationContext(),Verification_Code.class);
                            in.putExtra("email",prevmail);
                            in.putExtra("phonenum",phnnumber);
                            startActivity(in);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                kl.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(phnnumber)) {
                            ll =  FirebaseDatabase.getInstance().getReference().child("Member").child(phnnumber);
                            ll.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String getmail = snapshot.child("email").getValue().toString().trim();
                                    shownam.setText(getmail);
                                    dialog.show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    okay.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                            startActivity(new Intent(getApplicationContext(),Login.class));
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}