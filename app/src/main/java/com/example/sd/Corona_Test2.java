package com.example.sd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Corona_Test2 extends AppCompatActivity {

    TextInputEditText gender, birthday, a;
    String firstname, lastname, phone, email;
    RadioButton loc;
    Button btn;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona__test2);

      /*  firstname = getIntent().getStringExtra("fname");
        lastname = getIntent().getStringExtra("lname");
        phone = getIntent().getStringExtra("phn");
        email = getIntent().getStringExtra("mail");*/

        gender = findViewById(R.id.gendr);
        birthday = findViewById(R.id.birth);
        a = findViewById(R.id.address);
        loc = findViewById(R.id.radioLocation);

        btn = findViewById(R.id.buttonnextt);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent in = new Intent(getApplicationContext(),Corona_Test3.class);
                in.putExtra("fnme",firstname);
                in.putExtra("lnme",lastname);
                in.putExtra("phnn",phone);
                in.putExtra("maiil",email);
                in.putExtra("gendrr",gender.getText().toString().trim());
                in.putExtra("birth",birthday.getText().toString().trim());
                in.putExtra("address", adress.getText().toString().trim());
                startActivity(in);*/

               if (ActivityCompat.checkSelfPermission(Corona_Test2.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(Corona_Test2.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location != null){
                    try {
                        Geocoder geocoder = new Geocoder(Corona_Test2.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(),1);

                        // Set Latitude on TextView
                        //a.setText(addresses.get(0).getAddressLine(0).toString()
                        a.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</b><br></font>" +addresses.get(0).getAddressLine(0)));
                       // a.setText(Html.fromHtml("<font color='#6200EE'><b>Country Name :</b><br><b>Address :</b><br><b>Locality :</b><br></font>" +addresses.get(0).getCountryName()+addresses.get(0).getAddressLine(0)+addresses.get(0).getLocality()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}