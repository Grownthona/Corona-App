package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verification_Code extends AppCompatActivity {

    PinView pinFromUser;
    String phoneNom ,ml;
    String verificationCodeBySystem,otp;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView resendcde;
    int flag = 0;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verification__code);
        pinFromUser = findViewById(R.id.pin_view);

        resendcde = findViewById(R.id.resendcode);
        progressBar= findViewById(R.id.progressBar5);
        phoneNom = getIntent().getStringExtra("phonenum");
        ml = getIntent().getStringExtra("email");
        mAuth = FirebaseAuth.getInstance();
        sendVerificationCodeToUser(phoneNom);

        dialog = new Dialog(Verification_Code.this);
        dialog.setContentView(R.layout.password_notmatch);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        resendcde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCodeToUser(phoneNom);
            }
        });
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationCodeBySystem = s;
                    Toast.makeText(Verification_Code.this,"Code sent",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null){
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }
                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(Verification_Code.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            };
    private void verifyCode(String codeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInTheUserByCredential(credential);
    }

    private void signInTheUserByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verification_Code.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            //dialog.show();
                            Toast.makeText(Verification_Code.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent in = new Intent(getApplicationContext(),lastsign_up.class);
                            in.putExtra("phonenum",phoneNom);
                            in.putExtra("email",ml);
                            startActivity(in);
                        }
                    }
                });
    }


     public void callnextscreen(View view) {

        String codee = pinFromUser.getText().toString();
         verifyCode(codee);

        /*if(!codee.isEmpty() && flag!=1){
            verifyCode(codee);
            Intent in = new Intent(getApplicationContext(),lastsign_up.class);
            in.putExtra("email",ml);
            in.putExtra("phonenum",phoneNom);
            startActivity(in);
        }
        else
        {
            dialog.show();
        }*/
    }
}