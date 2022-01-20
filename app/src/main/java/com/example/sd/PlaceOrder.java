package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class PlaceOrder extends AppCompatActivity {

    String amnt,Totalmoney,deli;
    DatabaseReference rf,kl,ll;
    TextView showtotal,showpayment,subtot;
    Button btn;
    addCart cart;
    signin n;
    Random r;
    products p;
    TextView cartchild,getnum,adress;
    FirebaseUser user;
    double childcount;
    DatabaseReference reference,reff,addtoproductlist;
    String fullname,address;
    String userid;

    confirmorder adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        amnt = getIntent().getStringExtra("ammount");
        Totalmoney = getIntent().getStringExtra("total");

        showtotal = findViewById(R.id.textView47);
        showpayment = findViewById(R.id.textView59);
        subtot = findViewById(R.id.textView62);
        showpayment.setText(Totalmoney);
        btn = findViewById(R.id.button5);

        adress = findViewById(R.id.textView56);

        showtotal.setText(Totalmoney);
        subtot.setText(amnt);
        n = new signin();
        r = new Random();

        cart =new addCart();
        p = new products();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
        // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                     fullname =  mem.FullName;
                     address = mem.Address;
                     adress.setText(address);
                    reff = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
                    // medicinename.setText(phonenumber);
                }
                else
                {
                    Toast.makeText(PlaceOrder.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<addCart> options =
                new FirebaseRecyclerOptions.Builder<addCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid), addCart.class)
                        .build();
        adapter=new confirmorder(options);
        recyclerView.setAdapter(adapter);



        rf = FirebaseDatabase.getInstance().getReference().child("FinalCart").child(userid);
        addtoproductlist = FirebaseDatabase.getInstance().getReference().child("ProductRequests");

        addtoproductlist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    childcount = (snapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snapshot1: snapshot.getChildren()){
                            String name = String.valueOf(snapshot1.child("product").getValue());
                            String productid = String.valueOf(snapshot1.child("id").getValue());
                            String price = String.valueOf(snapshot1.child("price").getValue());

                            cart.setProduct(name);
                            cart.setUsername(fullname);
                            cart.setPrice(price);
                            cart.setTotal(Totalmoney);
                            cart.setId(productid);


                            final Random random = new Random();
                            int listt = random.nextInt(1000)+ random.nextInt(1000);
                            p.setProduct(name);
                            p.setPrice(price);
                            p.setTotal(Totalmoney);
                            p.setId(productid);
                            p.setUsername(fullname);
                            p.setUserAddress(address);
                            p.setUserid(userid);

                            p.setListid(String.valueOf(listt));

                            rf.child(String.valueOf(listt)).setValue(cart);
                            addtoproductlist.child(String.valueOf(listt)).setValue(p);
                            databaseReference.removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent in = new Intent(PlaceOrder.this,OderDetails.class);
                in.putExtra("tot",Totalmoney);
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