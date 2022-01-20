package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Medicine_Catagory extends AppCompatActivity {
    RecyclerView recview,reciew2;
    catagoryclss adapter;
    Catagory_Adapter catagory_adapter;
    String email;
    Button btn;
    String quan,name,medprice,catgry,id,phonenumber,imgurl;
    ConstraintLayout constraintLayout;
    ImageView hme;

    TextView cartchild,totalprice;
    FirebaseUser user;
    DatabaseReference reference,reff;
    String userid;
    long childcount =0;
    int sum=0;
    int fsum = 0;

    TextView t,medcat,covcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__catagory);

        catgry = getIntent().getStringExtra("catagory");



        t = findViewById(R.id.textView73);


        cartchild = findViewById(R.id.textView958);
        totalprice = findViewById(R.id.textView979);
        constraintLayout = findViewById(R.id.cartshiwbuttonlayout5);


        ////med = findViewById(R.id.textView73);
       // essen = findViewById(R.id.textView73);

       /* essen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medicine_Catagory.this,Essential_Catagory.class);

                startActivity(in);
            }
        });*/


       // btn = findViewById(R.id.button3);
        reciew2 = findViewById(R.id.recyclerView4);
        hme = findViewById(R.id.imageView64);

        reciew2.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        FirebaseRecyclerOptions<model> options2 =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Catagory"), model.class)
                        .build();
        catagory_adapter=new Catagory_Adapter(options2);
        reciew2.setAdapter(catagory_adapter);


        recview=(RecyclerView)findViewById(R.id.reccatgk);
        recview.setLayoutManager(new GridLayoutManager(this,2));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference(catgry), model.class)
                        .build();
        adapter=new catagoryclss(options);
        recview.setAdapter(adapter);

       /* medcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //underline01.setVisibility(View.VISIBLE);
                //underline02.setVisibility(View.INVISIBLE);
            }
        });
        covcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //underline02.setVisibility(View.VISIBLE);
                //underline01.setVisibility(View.INVISIBLE);
            }
        });*/


       user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    phonenumber = mem.Phone;
                    // medicinename.setText(phonenumber);
                    //constraintLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(Medicine_Catagory.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        reff = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    childcount = (snapshot.getChildrenCount());
                    cartchild.setText(String.valueOf(childcount));
                    if(childcount>=1){
                        constraintLayout.setVisibility(View.VISIBLE);
                    }
                    else{
                        constraintLayout.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
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
                    fsum = sum+50;
                    totalprice.setText(Integer.toString(fsum)+" "+"Tk");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medicine_Catagory.this,Cart.class);
                startActivity(in);
            }
        });

        hme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medicine_Catagory.this,Catagories.class);
                startActivity(in);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        catagory_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        catagory_adapter.startListening();
    }
}