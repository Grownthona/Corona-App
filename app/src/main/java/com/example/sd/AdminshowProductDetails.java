package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class AdminshowProductDetails extends AppCompatActivity {

    TextView catagory,medicinename,price,productid,user,address;
    String cat,med,p,pidd,uname,medaddress,userid,root;
    DatabaseReference reference,reff,addtoproductlist;
    Button btn;
    ProductReviews reviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshow_product_details);
        catagory = findViewById(R.id.ctgryy);
        cat = getIntent().getStringExtra("criteria"); //string
        catagory.setText(cat);

        medicinename = findViewById(R.id.mednme);
        med = getIntent().getStringExtra("medname"); //string
        medicinename.setText(med);

        price = findViewById(R.id.textView53);
        p = getIntent().getStringExtra("cost"); //string
        price.setText(p);

        productid = findViewById(R.id.textView149);
        pidd = getIntent().getStringExtra("pid"); //string
        productid.setText(pidd);

        user = findViewById(R.id.textView151);
        uname = getIntent().getStringExtra("unamee"); //string
        user.setText(uname);

        address = findViewById(R.id.textView153);
        medaddress = getIntent().getStringExtra("thikana"); //string
        address.setText(medaddress);

        btn = findViewById(R.id.deliverbtn);

        userid = getIntent().getStringExtra("userid");
        root = getIntent().getStringExtra("listunique");
        reviews = new ProductReviews();

        reference = FirebaseDatabase.getInstance().getReference("ProductRequests").child(root);
        reff = FirebaseDatabase.getInstance().getReference("TakeReview").child(userid).child("10/15/2021");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random r = new Random();

                int id = r.nextInt(1000);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        reviews.setProductname(med);
                        reviews.setProductprice(p);
                        reff.child(String.valueOf(id)).setValue(reviews);

                        reference.removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

       // reff = FirebaseDatabase.getInstance().getReference("ProductRequests").child(userid);
    }
}