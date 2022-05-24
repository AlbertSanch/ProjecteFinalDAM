package com.example.projectemapa;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectemapa.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    public boolean fermarker;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button menu = findViewById(R.id.menu);
        FragmentManager fmenu = getSupportFragmentManager();
        OpcionsMenu OpcionsMenu = new OpcionsMenu();

        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fmenu.beginTransaction().replace(R.id.contenidor,OpcionsMenu).commit();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

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
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        if (checkLocationPermission()) {
            mMap.setMyLocationEnabled(true);
        }
        LocationManager gestorLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gestorLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        Location loc = gestorLoc.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        LatLng mevaUbi = new LatLng(loc.getLatitude(), loc.getLongitude());
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(mevaUbi, 15);

        mMap.moveCamera(camera);
    }
    public boolean checkLocationPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        return false;


    }
    public boolean onMarkerClick(final Marker marker) {
           

        // Return false to indicate that we have not consumed the event and that we wish
            // for the default behavior to occur (which is for the camera to move such that the
            // marker is centered and for the marker's info window to open, if it has one).
            return false;
        }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        LatLng posicio = new LatLng(latLng.latitude, latLng.longitude);
        Intent intent = new Intent(this, Info_Marker.class);
        intent.putExtra("valor", fermarker);

        MarkerOptions options1 = new MarkerOptions();
        LatLng marquer = new LatLng(posicio.latitude, posicio.longitude);
        options1.position(marquer);
        mMap.addMarker(options1);
        startActivity(intent);



        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

}