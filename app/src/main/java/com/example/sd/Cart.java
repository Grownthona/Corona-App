package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cart extends AppCompatActivity {

    int sum=0;
    int fsum = 0;
    TextView totalprice,ftotal;
    RecyclerView recview,reciew2;
    ShowCart adapter;
    Button btn;
    signin n;
    String email, number,phonenumber;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Member mem;

    TextView cartchild,getnum;
    FirebaseUser user;
    DatabaseReference reference,reff;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalprice = findViewById(R.id.total);
        ftotal = findViewById(R.id.textView40);
        btn = findViewById(R.id.button2);

        n = new signin();
        number = getIntent().getStringExtra("phn");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
        // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;
                    // medicinename.setText(phonenumber);
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot snapshot1: snapshot.getChildren()){
                                String value = String.valueOf(snapshot1.child("price").getValue());
                                String quan = String.valueOf(snapshot1.child("quantity").getValue());
                                int y = Integer.parseInt(quan);
                                String idd = String.valueOf(snapshot1.child("id").getValue());
                                if(y<=0)
                                {
                                    databaseReference.removeValue();
                                }
                                sum+= Integer.parseInt(value);
                                totalprice.setText(Integer.toString(sum)+" "+"Tk");
                                fsum = sum+50;
                                ftotal.setText(Integer.toString(fsum)+" "+"Tk");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(Cart.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recview=(RecyclerView)findViewById(R.id.recyclerView);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<addCart> options =
                new FirebaseRecyclerOptions.Builder<addCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid), addCart.class)
                        .build();
        adapter=new ShowCart(options);
        recview.setAdapter(adapter);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Cart.this,PlaceOrder.class);
                in.putExtra("ammount", totalprice.getText().toString());
                in.putExtra("total",Integer.toString(fsum));
               startActivity(in);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}