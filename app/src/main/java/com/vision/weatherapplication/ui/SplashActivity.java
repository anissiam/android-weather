package com.vision.weatherapplication.ui;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.vision.weatherapplication.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        boolean userGiveLocationPermission = ActivityCompat.checkSelfPermission(getApplicationContext(),
                "android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED;
        if (!userGiveLocationPermission) {
            askForPermission();
        }else {
            pickLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void pickLocation() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d("sdasdsdsd", location.getLatitude() + " : " + location.getLongitude());
                //location.get
                Geocoder geocoder = new Geocoder(getApplicationContext() );
                try {
                    List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    Log.d("sdasdsdsd", address.get(0).getLocality()+ ":");
                     Intent intent =new Intent(getBaseContext(), HomeActivity.class);
                    intent.putExtra("lat", location.getLatitude());
                    intent.putExtra("lag", location.getLongitude());
                    startActivity(intent);
                    finish();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("sdasdsdsd", e.getMessage());
            }
        });
    }

    private void askForPermission() {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    ActivityResultLauncher<String> launcher  = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) {
                pickLocation();
                Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "you don't have permission to use the app", Toast.LENGTH_LONG).show();
            }
        }
    });

}