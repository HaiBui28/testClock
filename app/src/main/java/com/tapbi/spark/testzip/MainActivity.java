package com.tapbi.spark.testzip;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Granularity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.tapbi.spark.testzip.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private GoogleSignInClient googleSignInClient;
    GoogleSignInOptions signInOptions;
    FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        binding.btnLoginDriver.setOnClickListener(v -> {
            if (checkPermissionLocation(this)) {
                setUpLocationUpdate();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }
        });
        binding.btnClock.setOnClickListener(v->{
            startActivity(new Intent(this,ClockActivity.class));
        });
    }

    private void setUpLocationUpdate() {
        LocationRequest locationRequest = new LocationRequest.Builder(5000)
                .setGranularity(Granularity.GRANULARITY_FINE)
                .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
                .setMinUpdateDistanceMeters(100)
                .build();
        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(settingsRequest).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Log.d("Haibq", "setUpLocationUpdate: gg");
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
            } else {
                Log.d("Haibq", "setUpLocationUpdate: ");
            }
        });

    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Log.d("Haibq", "onLocationResult: " + locationResult);
        }
    };
//    public void loginDrive(Intent intent) {
//            GoogleSignIn.getSignedInAccountFromIntent(intent)
//                    .addOnSuccessListener(googleSignInAccount -> {
//                        ((MainActivity) fragmentActivity).googleSignInAccount = googleSignInAccount;
//                        if (clickAccount) {
//                            showGGDriveDetail(googleSignInAccount);
//                        } else {
//                            setTextDefault();
////                            accountAdapter.checkEditAccount(false);
////                            addAccount(googleSignInAccount.getEmail(), GOOGLE_DRIVE, R.string.logged_in, null, googleSignInAccount.getId());
//                        }
//                    })
//                    .addOnFailureListener(e -> {
//                        Utils.customToast(fragmentActivity, this.getString(R.string.login_drive_failed));
//                    });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("Haibq", "onActivityResult:1 ");

        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Haibq", "onActivityResult:2 ");
        if (requestCode == 100) {
            Log.d("Haibq", "onActivityResult: " + data);
            GoogleSignIn.getSignedInAccountFromIntent(data)
                    .addOnSuccessListener(googleSignInAccount -> {
                        Log.d("Haibq", "onActivityResult: " + googleSignInAccount.getEmail());
////                        ((MainActivity) fragmentActivity).googleSignInAccount = googleSignInAccount;
//                        if (clickAccount) {
////                            showGGDriveDetail(googleSignInAccount);
//                        } else {
////                            removeAccount(GOOGLE_DRIVE);
//                            setTextDefault();
//                            accountAdapter.checkEditAccount(false);
//                            addAccount(googleSignInAccount.getEmail(), GOOGLE_DRIVE, R.string.logged_in, null, googleSignInAccount.getId());
//                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.d("Haibq", "onActivityResult: 6");
//                        Utils.customToast(fragmentActivity, this.getString(R.string.login_drive_failed));
                    });
        }
    }

    private Boolean checkPermissionLocation(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;
    }
}