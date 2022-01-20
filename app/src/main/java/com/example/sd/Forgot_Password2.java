package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forgot_Password2 extends AppCompatActivity {

    PinView pinFromUser;
    String phoneNom ,ml;
    String verificationCodeBySystem;
    FirebaseAuth mAuth;
    EditText mailtext;
    Button btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot__password2);
        mailtext = findViewById(R.id.emailrp);
        btn = findViewById(R.id.next1);
        auth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mailtext.getText().toString().trim().isEmpty()){
                    mailtext.setError("Please Enter an Email Address");
                }
                else{
                    auth.sendPasswordResetEmail(mailtext.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Forgot_Password2.this,"Email sent successfully to reset password",Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(getApplicationContext(),Login.class);
                                startActivity(in);
                            }
                            else{
                                Toast.makeText(Forgot_Password2.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }

}