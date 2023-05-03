package com.example.cobapeta;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.core.app.ActivityCompat;

import android.location.LocationProvider;
import android.os.Bundle;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cobapeta.databinding.ActivityMapsBinding;


import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final private int REQUEST_COURSE_ACCESS = 123;
    boolean permissionGranted = false;
    private GoogleMap mMap;
    LocationManager lm;
    LocationListener locationListener;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COURSE_ACCESS);
            return;
        } else {
            permissionGranted = true;
        } if(permissionGranted) {
            lm.removeUpdates(locationListener);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COURSE_ACCESS);
            return;
        } else {
            permissionGranted = true;
        }
        if (permissionGranted) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
//            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            locationListener = new MyLocationListener();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                          int[] grandResult){
        switch (requestCode){
            case REQUEST_COURSE_ACCESS:
                if (grandResult[0] == PackageManager.PERMISSION_GRANTED){
                    permissionGranted = true;
                } else {
                    permissionGranted = false;
                } break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grandResult);
        }
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location){
            if(location != null){
                Toast.makeText(getBaseContext(), "Location changed : Lat: " +
                        location.getLatitude() + "\nLng: "
                        + location.getLongitude(), Toast.LENGTH_SHORT).show();
                LatLng p = new LatLng((int) (location.getLatitude()),
                        (int) (location.getLongitude()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
            }
        }

        @Override
        public void onProviderDisabled(String provider){
            Toast.makeText(getBaseContext(), provider + " Disabled",
                    Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onProviderEnabled(String provider){
            Toast.makeText(getBaseContext(), provider + " Enabled",
                    Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){
            String statusString = "";
            switch (status){
                case LocationProvider.AVAILABLE:
                    statusString = "Available";
                case LocationProvider.OUT_OF_SERVICE:
                    statusString = "Out of Service";
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    statusString = "Temporarily Unavailable";
            }
            Toast.makeText(getBaseContext(), provider + " " + statusString,
                    Toast.LENGTH_SHORT).show();
        }
        }
}