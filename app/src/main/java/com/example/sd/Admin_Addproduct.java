package com.example.sd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.webkit.MimeTypeMap;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Admin_Addproduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AutoCompleteTextView spinner;
    EditText pname,pprice,pdescription,pid,purl,maincatagory;
    Button finaladd;
    FirebaseStorage storage;

    EditText uploadbtn;
    ImageView back;

    String details,name,medprice,catgry,id,keyyy,imgurl,num;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    DatabaseReference reff,reffmed,reffessen,maskref,Disinfectref,handsanitizerref,emergen,essential,medicines;
    StorageReference mStorageref;
    Dialog dialog;
    model cart;
    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__addproduct);

        storage = FirebaseStorage.getInstance();

        mStorageref = FirebaseStorage.getInstance().getReference();

        details = getIntent().getStringExtra("about");
        name = getIntent().getStringExtra("medname");
        medprice = getIntent().getStringExtra("cost");
        catgry = getIntent().getStringExtra("criteria");
        id = getIntent().getStringExtra("pid");
        imgurl = getIntent().getStringExtra("url");
        //keyyy = getIntent().getStringExtra("message");

        spinner = findViewById(R.id.autoC);
        uploadbtn = findViewById(R.id.uploadbutton2);
        pname = findViewById(R.id.prname);
        pprice = findViewById(R.id.prPrice);
        pdescription = findViewById(R.id.prDescription);
        pid = findViewById(R.id.Prid);
        finaladd = findViewById(R.id.button12);
        maincatagory = findViewById(R.id.maincat);
        back = findViewById(R.id.imageView57);

        pdescription.setText(details);
        pname.setText(name);
        pprice.setText(medprice);
        pid.setText(id);

        cart =  new model();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin_Addproduct.this,Admin_Dashboard.class);
                startActivity(in);
            }
        });


        reff = FirebaseDatabase.getInstance().getReference().child("Medical").child(pid.getText().toString());
        reffmed =  FirebaseDatabase.getInstance().getReference().child("Medicine").child(pid.getText().toString());
        maskref = FirebaseDatabase.getInstance().getReference().child("Mask").child(pid.getText().toString());
        Disinfectref = FirebaseDatabase.getInstance().getReference().child("Disinfect").child(pid.getText().toString());
        handsanitizerref = FirebaseDatabase.getInstance().getReference().child("HandSanitizer").child(pid.getText().toString());
        emergen = FirebaseDatabase.getInstance().getReference().child("Emergency").child(pid.getText().toString());
        essential = FirebaseDatabase.getInstance().getReference().child("Essential").child(pid.getText().toString());
        medicines = FirebaseDatabase.getInstance().getReference().child("Medicines").child(pid.getText().toString());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.catagories, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        finaladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase(imageUri);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        cart.setName(pname.getText().toString());
                        cart.setPrice(pprice.getText().toString());
                        cart.setCatagory(spinner.getText().toString());
                        cart.setDescription(pdescription.getText().toString());
                        cart.setId(pid.getText().toString());


                        reff.child(pid.getText().toString()).setValue(cart);
                        if(maincatagory.getText().toString().equals("Essential")){
                            essential.child(pid.getText().toString()).setValue(cart);
                        }
                        if(maincatagory.getText().toString().equals("Medicines")){
                            medicines.child(pid.getText().toString()).setValue(cart);
                        }
                        if(spinner.getText().toString().equals("Medicine")){
                            reffmed.child(pid.getText().toString()).setValue(cart);
                        }else if(spinner.getText().toString().equals("Mask")) {
                            maskref.child(pid.getText().toString()).setValue(cart);
                        }
                        else if(spinner.getText().toString().equals("Disinfect")) {
                            Disinfectref.child(pid.getText().toString()).setValue(cart);
                        }
                        else if(spinner.getText().toString().equals("Hand Sanitizer")) {
                            handsanitizerref.child(pid.getText().toString()).setValue(cart);
                        }
                        else if(spinner.getText().toString().equals("Emergency")) {
                            emergen.child(pid.getText().toString()).setValue(cart);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

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
                        cart.setUrl(uri.toString());
                        //String modelId = root.push().getKey();
                       // root.child(modelId).setValue(modell);
                        Toast.makeText(Admin_Addproduct.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Admin_Addproduct.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
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

   /* private void FileUploader(){

        if(imguri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);

            StorageReference ref = mStorageref.child("images");
            ref.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(Admin_Addproduct.this, "Uploaded", Toast.LENGTH_SHORT).show();
                           ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                 
                               }
                           });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Admin_Addproduct.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());

                        }
                    })
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}