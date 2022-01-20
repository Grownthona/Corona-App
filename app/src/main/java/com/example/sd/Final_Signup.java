package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Final_Signup extends AppCompatActivity {

    Button bft;
    String phnnumber,email,password,passname,addess,cat;
    DatabaseReference reff,reff2;
    TextView fpass,showname;
    Member member;
    Dialog dialog;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__signup);
        bft = findViewById(R.id.nextf);
        email = getIntent().getStringExtra("emaill");
        phnnumber = getIntent().getStringExtra("phonenumm");
        password = getIntent().getStringExtra("passwordd");
        passname = getIntent().getStringExtra("name");
        addess = getIntent().getStringExtra("Address");
        cat = getIntent().getStringExtra("Catagory");

        showname = findViewById(R.id.fillname);
        showname.setText(email);

        /*     Custom dialogue codes ------>        */

        dialog = new Dialog(Final_Signup.this);
        dialog.setContentView(R.layout.signup_success);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.nextsignin);

        /*     Custom dialogue codes ------>        */


        fAuth = FirebaseAuth.getInstance();
        member =new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        //reff2 = FirebaseDatabase.getInstance().getReference().child("Member").child(cat);
        bft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent in = new Intent(Final_Signup.this,Login.class);
                //startActivity(in);


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(Final_Signup.this,"Succesfully Registered",Toast.LENGTH_SHORT).show();

                            /*     Custom dialogue codes ------>        */
                            member.setPhone(phnnumber);
                            member.setEmail(email);
                            member.setFullName(passname);
                            member.setAddress(addess);
                            member.setCatagory(cat);
                            reff.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(member);
                            //reff2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(member);
                            dialog.show();
                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                }
                            });
                            /*     Custom dialogue codes ------>        */
                        }
                        else
                        {
                            Toast.makeText(Final_Signup.this,"Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}