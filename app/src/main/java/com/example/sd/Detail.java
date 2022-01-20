package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class Detail extends AppCompatActivity {

    int addcrt=0;
    String details,name,medprice,catgry,id,keyyy,imgurl,num;
    TextView shwdetail,medicinename,catagory,price,number,cartchild;
    DatabaseReference reff,tt;

    Dialog dialog;
    ImageView add,minimize,img,cartimg;
    int count = 1;
    long childcount =0;
    addCart cart;
    Button btn;
    Medical_Dashboard key;
    String phonenumber;
    FirebaseAuth auth;


    FirebaseUser user;
    DatabaseReference reference;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        details = getIntent().getStringExtra("about");
        name = getIntent().getStringExtra("medname");
        medprice = getIntent().getStringExtra("cost");
        catgry = getIntent().getStringExtra("criteria");
        id = getIntent().getStringExtra("pid");
        imgurl = getIntent().getStringExtra("url");
        keyyy = getIntent().getStringExtra("message");

        shwdetail = findViewById(R.id.textView29);
        medicinename = findViewById(R.id.mednme);
        catagory = findViewById(R.id.ctgryy);
        price = findViewById(R.id.textView53);
        number = findViewById(R.id.textView36);
        btn = findViewById(R.id.deliverbtn);
        img = findViewById(R.id.imageView21);

        Picasso.get().load(imgurl).into(img);

       add = findViewById(R.id.addsdd);
       minimize = findViewById(R.id.minusss);

        shwdetail.setText(details);
        medicinename.setText(name);
        catagory.setText(catgry);
        price.setText(medprice);

        dialog = new Dialog(Detail.this);
        dialog.setContentView(R.layout.takephonenumber);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.button6);
        EditText takenum = dialog.findViewById(R.id.phonenumm);


        cart =new addCart();

       /* auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if(firebaseUser == null){
            Toast.makeText(Detail.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
        else
        {
            shwdetail.setText(userid);
        }*/

        /* The user information fetching code */

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();
       // shwdetail.setText(userid);

        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    String fullname =  mem.FullName;
                    phonenumber = mem.Phone;
                    reff = FirebaseDatabase.getInstance().getReference().child("AddCart").child(userid);
                   // medicinename.setText(phonenumber);
                }
                else
                {
                    Toast.makeText(Detail.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        /*End of  The user information fetching code */


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >=0) {
                    count += 1;
                    number.setText("" + count);
                    int p = Integer.parseInt(medprice);
                    price.setText(Integer.toString(p));
                }
            }
        });

        minimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count -= 1;
                if(count >=1) {
                    number.setText("" + count);
                    int p = Integer.parseInt(medprice);
                    int r= p*count;
                    price.setText(Integer.toString(r));
                }
                else if(count<1)
                {

                    reff.child(id).removeValue();
                    Toast.makeText(Detail.this, "This product is deleted from the cart list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // cartimg.setVisibility(View.VISIBLE);
               // cartchild.setVisibility(View.VISIBLE);

                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       /* if (snapshot.hasChild(id) && !keyyy.equals("Update")) {
                            Toast.makeText(Detail.this, "This product is already added in the cart list.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {*/
                               addcrt++;
                                Intent in = new Intent(Detail.this,Catagories.class);
                                cart.setPhone(phonenumber);
                                in.putExtra("key",phonenumber);
                                cart.setProduct(name);
                                cart.setPrice(price.getText().toString());
                                cart.setCatagory(catgry);
                                cart.setQuantity(Integer.toString(count));
                                cart.setId(id);
                                cart.setImg(imgurl);

                                reff.child(id).setValue(cart);

                                startActivity(in);

                       // }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


    }
}