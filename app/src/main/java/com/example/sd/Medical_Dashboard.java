package com.example.sd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.UUID;

public class Medical_Dashboard extends AppCompatActivity {

    RecyclerView recview,reciew2,recview3;
    CardView cat,uploadbtn;
    String email;
    Medicines adapter;
    Meddashrec adapter2;
    catagoryclss adapter3;
    TextView jj,medic;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    DatabaseReference addtoproductlist,prescriptionphoto,pres;
    Dialog dialog;
    model cart;
    Member prescription;

    FirebaseUser user;
    String fullname,userphone,useraddress;
    String userid;


    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__dashboard);


        cart = new model();
        prescription = new Member();

        addtoproductlist = FirebaseDatabase.getInstance().getReference().child("ProductRequests");
        prescriptionphoto = FirebaseDatabase.getInstance().getReference().child("Prescriptions");

        cat = findViewById(R.id.cardView7);
        uploadbtn = findViewById(R.id.uploadbutton);
        email = getIntent().getStringExtra("mail");

        recview3 = findViewById(R.id.emergency);


        user = FirebaseAuth.getInstance().getCurrentUser();
        pres = FirebaseDatabase.getInstance().getReference("Member");
        userid = user.getUid();

        jj = findViewById(R.id.textView100);
        medic = findViewById(R.id.textView32);

        jj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medical_Dashboard.this,Essential_Catagory.class);
                in.putExtra("mail",email);
                startActivity(in);
            }
        });

        medic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medical_Dashboard.this,Essential_Catagory.class);
                in.putExtra("mail",email);
                startActivity(in);
            }
        });

        pres.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Member mem = snapshot.getValue(Member.class);
                if(mem != null){
                    fullname =  mem.FullName;
                    useraddress = mem.Address;
                    userphone = mem.Phone;
                }
                else
                {
                    Toast.makeText(Medical_Dashboard.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        dialog = new Dialog(Medical_Dashboard.this);
        dialog.setContentView(R.layout.uploadimage);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.medicinebackground));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button okay = dialog.findViewById(R.id.uploadbtnn);
        Button b = dialog.findViewById(R.id.button3);
        ProgressBar progressBar = dialog.findViewById(R.id.progressBar6);

        //recview=(RecyclerView)findViewById(R.id.recview);
        reciew2 = (RecyclerView)findViewById(R.id.recyclerView2);


       /* recview3.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Emergency"), model.class)
                        .build();
        adapter3=new catagoryclss(options);
        recview3.setAdapter(adapter3);*/

        reciew2.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        FirebaseRecyclerOptions<meddash> options2 =
                new FirebaseRecyclerOptions.Builder<meddash>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Dashboard"), meddash.class)
                        .build();
        adapter2=new Meddashrec(options2);
        reciew2.setAdapter(adapter2);

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();
                dialog.show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase(imageUri);
            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random random = new Random();
                int listt = random.nextInt(1000)+ random.nextInt(1000);
                Toast.makeText(Medical_Dashboard.this, "Your Order has been placed Sucessfully.We will contact with soon via voicemail", Toast.LENGTH_LONG).show();

                prescriptionphoto.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        prescription.setFullName(fullname);
                        prescription.setAddress(useraddress);
                        prescription.setPhone(userphone);
                        prescriptionphoto.child(String.valueOf(listt)).setValue(prescription);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dialog.dismiss();
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Medical_Dashboard.this,Catagories.class);
                in.putExtra("mail",email);
                startActivity(in);
            }
        });
    }


    private  String getExtention(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Filechooser() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent , 2);
    }
    private void uploadToFirebase(Uri uri) {

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //model modell = new model(uri.toString());
                        prescription.setUrl(uri.toString());
                        //String modelId = root.push().getKey();
                        // root.child(modelId).setValue(modell);

                        Toast.makeText(Medical_Dashboard.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Medical_Dashboard.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            //image.setImageIRI(imguri);
        }
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


    @Override
    protected void onStart() {
        super.onStart();
       // adapter.startListening();
        adapter2.startListening();
        //adapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // adapter.stopListening();
        adapter2.stopListening();
      //  adapter3.stopListening();
    }

}