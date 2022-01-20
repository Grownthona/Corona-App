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

public class Catagories extends AppCompatActivity {

    RecyclerView recview,reciew2;
    catagoryclss adapter;
    Catagory_Adapter catagory_adapter;
    String email;
    Button btn;
    String quan,name,medprice,catgry,id,phonenumber,imgurl;
    ConstraintLayout constraintLayout;
    ImageView ij,home,back;

    TextView cartchild,totalprice,shownumber,goessen,gomed;
    FirebaseUser user;
    DatabaseReference reference,reff;
    String userid;
    long childcount =0;
    int sum=0;
    int fsum = 0;

    TextView t,med,essen,underline01,underline02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);



        t = findViewById(R.id.textView73);

        ij = findViewById(R.id.imageView33);


        cartchild = findViewById(R.id.textView958);
        totalprice = findViewById(R.id.textView979);
        constraintLayout = findViewById(R.id.cartshiwbuttonlayout5);
        shownumber = findViewById(R.id.showcartnumber);
        essen = findViewById(R.id.Essen);
        back = findViewById(R.id.imageView61);

        home = findViewById(R.id.imageView64);

        reciew2 = findViewById(R.id.recyclerView4);


        email = getIntent().getStringExtra("mail");

        recview=(RecyclerView)findViewById(R.id.reccat);
        recview.setLayoutManager(new GridLayoutManager(this,2));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Medical"), model.class)
                        .build();
        adapter=new catagoryclss(options);
        recview.setAdapter(adapter);

        reciew2.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        FirebaseRecyclerOptions<model> options2 =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Catagory"), model.class)
                        .build();
        catagory_adapter=new Catagory_Adapter(options2);
        reciew2.setAdapter(catagory_adapter);



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
                    Toast.makeText(Catagories.this, "Something wrong", Toast.LENGTH_SHORT).show();
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
                    shownumber.setText(String.valueOf(childcount));
                    if(childcount>=1){
                        constraintLayout.setVisibility(View.VISIBLE);
                        shownumber.setVisibility(View.VISIBLE);
                        ij.setVisibility(View.VISIBLE);
                    }
                    else{
                        constraintLayout.setVisibility(View.INVISIBLE);
                        shownumber.setVisibility(View.INVISIBLE);
                        ij.setVisibility(View.INVISIBLE);
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
                    totalprice.setText(Integer.toString(sum)+" "+"Tk");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Catagories.this,Cart.class);
                startActivity(in);
            }
        });

        essen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Catagories.this,Essential_Catagory.class);
                startActivity(in);
            }
        });

        gomed = findViewById(R.id.Medicines);
        gomed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Catagories.this,Medi.class);
                startActivity(in);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Catagories.this,Dashboard.class);
                startActivity(in);
            }
        });


    }
    @Override
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