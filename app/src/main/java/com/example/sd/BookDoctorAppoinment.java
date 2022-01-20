package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class BookDoctorAppoinment extends AppCompatActivity {

    String dname ,dspec,dtime,docdes,docpic,appoinmentdate;
    TextView sdate,stime,doctorname,patienphone,pname;
    Button btn;
    ImageView img;

    Doctorappoinment doc;

    FirebaseUser user;
    DatabaseReference reference,admindoc,patientdoc;
    String userid,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doctor_appoinment);
        dname = getIntent().getStringExtra("name");
        dspec = getIntent().getStringExtra("sprcial");
        dtime = getIntent().getStringExtra("time");
        docdes = getIntent().getStringExtra("about");
        docpic = getIntent().getStringExtra("url");
        appoinmentdate = getIntent().getStringExtra("date");

        btn = findViewById(R.id.button14);

        sdate= findViewById(R.id.textView129);
        sdate.setText(appoinmentdate);
        stime = findViewById(R.id.textView131);
        stime.setText(dtime);
        doctorname = findViewById(R.id.textView125);
        doctorname.setText(dname);
        img = findViewById(R.id.imageView44);
        patienphone = findViewById(R.id.textView135);
        pname = findViewById(R.id.textView140);
        Picasso.get().load(docpic).into(img);

        doc = new Doctorappoinment();
        admindoc = FirebaseDatabase.getInstance().getReference("AdminDocAppoinmentlist");
        patientdoc = FirebaseDatabase.getInstance().getReference("PateintAppoinmentHistory");


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;
                    patienphone.setText(mem.Phone);
                    pname.setText(mem.FullName);
                    email = mem.Email;
                    // medicinename.setText(phonenumber);
                }
                else
                {
                    Toast.makeText(BookDoctorAppoinment.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random random = new Random();
                int listt = random.nextInt(1000)+ random.nextInt(1000);
                Intent in = new Intent(BookDoctorAppoinment.this,Confirmvideocall.class);
                doc.setDoctorname(dname);
                doc.setPatientname(pname.getText().toString());
                doc.setPatientsphone(patienphone.getText().toString());
                doc.setAppoinmentdate(appoinmentdate);
                doc.setFees("500");
                doc.setPatientemail(email);
                admindoc.child(String.valueOf(listt)).setValue(doc);
                //patientdoc.child(userid).setValue(doc);
                startActivity(in);
            }
        });
    }
}