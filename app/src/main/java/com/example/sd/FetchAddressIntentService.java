package com.example.sd;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchAddressIntentService extends IntentService{


    private ResultReceiver addressResultReceiver;

    public FetchAddressIntentService(Handler handler){
        super("FetchAddressIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            String msg = "";
            //get result receiver from intent
            addressResultReceiver = intent.getParcelableExtra("add_receiver");

            if (addressResultReceiver == null) {
                Log.e("GetAddressIntentService",
                        "No receiver, not processing the request further");
                return;
            }

            Location location = intent.getParcelableExtra("add_location");

            if (location == null) {
                msg = "No location, can't go further without location";
                sendResultsToReceiver(0, msg);
                return;
            }

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;

            try {
                addresses = geocoder.getFromLocation(
                        location.getLatitude(),
                        location.getLongitude(),
                        1);
            } catch (Exception ioException) {
                Log.e("", "Error in getting address for the location");
            }

            if (addresses == null || addresses.size()  == 0) {
                msg = "No address found for the location";
                sendResultsToReceiver(1, msg);
            } else {
                Address address = addresses.get(0);
                StringBuffer addressDetails = new StringBuffer();

                addressDetails.append(address.getFeatureName());
                addressDetails.append("\n");

                addressDetails.append(address.getThoroughfare());
                addressDetails.append("\n");

                addressDetails.append("Locality: ");
                addressDetails.append(address.getLocality());
                addressDetails.append("\n");

                addressDetails.append("County: ");
                addressDetails.append(address.getSubAdminArea());
                addressDetails.append("\n");

                addressDetails.append("State: ");
                addressDetails.append(address.getAdminArea());
                addressDetails.append("\n");

                addressDetails.append("Country: ");
                addressDetails.append(address.getCountryName());
                addressDetails.append("\n");

                addressDetails.append("Postal Code: ");
                addressDetails.append(address.getPostalCode());
                addressDetails.append("\n");

                sendResultsToReceiver(2,addressDetails.toString());
            }
        }
    }
    private void sendResultsToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString("address_result", message);
        addressResultReceiver.send(resultCode, bundle);
    }
}
