package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.Map;

public class Mapas extends AppCompatActivity implements View.OnClickListener {

    private FusedLocationProviderClient mFusedLocationClient;
    private int MY_PERMISSION_REQUEST_READ_CONTACTS;
    private Button maps;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        LatLong();
        maps = findViewById(R.id.mapa);
        maps.setOnClickListener(this);
    }

    private void LatLong() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(com.example.npkadvisorfinal.Mapas.this, new String []{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_READ_CONTACTS);
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.e("Latitud",+location.getLatitude()+"Longitud"+location.getLongitude());
                            Toast.makeText(Mapas.this, "Medidas Obtenidas", Toast.LENGTH_LONG).show();


                        }
                        else
                        {
                            System.out.println("dañado");
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mapa : Intent intent = new Intent(Mapas.this, MapsActivity.class);
                             startActivity(intent);
                             break;
        }
    }
}