package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UpdateAddress extends AppCompatActivity {

    DatabaseReference reference,reff,reff2,addtoproductlist;
    FirebaseUser user;

    String Email;
    String Phone;
    String FullName;
    String url;
    String catagory;
    String userid;
    EditText address;
    Member mem;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
        // shwdetail.setText(userid);

        address = findViewById(R.id.addresss);
        confirm = findViewById(R.id.button27);

        mem = new Member();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    FullName =  mem.FullName;
                    Email = mem.Email;
                    Phone = mem.Phone;
                    url= mem.url;
                    catagory = mem.catagory;

                }
                else
                {
                    Toast.makeText(UpdateAddress.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mem.setPhone(Phone);
                mem.setAddress(address.getText().toString());
                mem.setFullName(FullName);
                mem.setEmail(Email);
                mem.setUrl(url);
                mem.setCatagory(catagory);
                reff.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mem);
                Toast.makeText(UpdateAddress.this, "Your Address has been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}