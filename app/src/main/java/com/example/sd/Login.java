package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText mail,pass;
    TextView signup_nextactivity ,fpass,l;
    DatabaseReference rf;
    Button b;
    ProgressBar progressBar;
    String fmail,fpassword,catagorytypeuser;

    signin n;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference userref;
    DatabaseReference reference,reff;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        signup_nextactivity = findViewById(R.id.signup);




        n = new signin();
        database = FirebaseDatabase.getInstance();
        userref = database.getReference("Member");

        progressBar = findViewById(R.id.loginprogress);
        b = findViewById(R.id.btnsignin);

        mail = findViewById(R.id.emailrk);
        pass = findViewById(R.id.passin);
        fpass = findViewById(R.id.forgotpassw);
        fAuth = FirebaseAuth.getInstance();

       /* user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    catagorytypeuser =  mem.catagory;

                }
                else
                {
                    Toast.makeText(Login.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/






        signup_nextactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Sign_Up1.class);
                startActivity(intent);
            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(Login.this,Forgot_Password2.class);
                startActivity(intentt);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();
               /* rf = FirebaseDatabase.getInstance().getReference().child("Member").child("1");
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String enmail = snapshot.child("email").getValue().toString();
                        String enpass = snapshot.child("password").getValue().toString();
                        if((mail.getText().toString().trim().equals(enmail)) && (pass.getText().toString().trim().equals(enpass)))
                        {
                            Intent intent2 = new Intent(Login.this,Dashboard.class);
                            startActivity(intent2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
                if(TextUtils.isEmpty(email)){
                    mail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    pass.setError("Password must be more than 6 digits");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                            if(email.equals("190104087@aust.edu")){

                                Intent in = new Intent(Login.this,Admin_Dashboard.class);

                                startActivity(in);
                            }else{
                                Intent in = new Intent(Login.this,Dashboard.class);
                                in.putExtra("mail",email);
                                startActivity(in);
                            }
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}