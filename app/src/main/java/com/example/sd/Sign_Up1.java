package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Sign_Up1 extends AppCompatActivity {

    Button nxt;
    EditText mail;
    Dialog dialog;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign__up1);

        nxt = findViewById(R.id.next1);
        fAuth = FirebaseAuth.getInstance();
        mail = findViewById(R.id.emailrk);

        dialog = new Dialog(Sign_Up1.this);
        dialog.setContentView(R.layout.back_signin);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.nextf);

        TextView shownam = dialog.findViewById(R.id.fillname);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mail.setError("Email is required");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mail.setError("Email pattern is incorrect");
                    return;
                }

               /* fAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = task.getResult().getSignInMethods().isEmpty();
                        if(check==true)
                        {
                            Intent in = new Intent(Sign_Up1.this,Sign_up2.class);
                            in.putExtra("keyname",email);
                            startActivity(in);
                        }
                        else
                        {
                            shownam.setText(mail.getText().toString().trim());
                            dialog.show();
                            okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                }
                            });
                        }
                    }
                });*/

                Intent in = new Intent(Sign_Up1.this,Sign_up2.class);
                in.putExtra("keyname",email);
                startActivity(in);
            }
        });
    }
}