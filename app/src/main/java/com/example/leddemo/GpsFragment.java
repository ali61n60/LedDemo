package com.example.leddemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.leddemo.model.FlashlightProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


public class GpsFragment extends Fragment implements LocationListener {
    public static final int AccessFineLocationRequestCode = 1;
    TextView textViewLat;
    protected LocationManager locationManager;

    Context parent;
    View rootView;

    public GpsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_gps, container, false);
        initGps();
        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AccessFineLocationRequestCode) {
            initGps();
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parent=context;
    }
    private void initGps() {

        if (ActivityCompat.checkSelfPermission(parent, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            List<String> listPermissionsNeeded = new ArrayList<>();
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions((Activity)parent, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), AccessFineLocationRequestCode);
            Log.d("Latitude", "Asking for Permission");
            return;

        }
        locationManager = (LocationManager) parent.getSystemService(Context.LOCATION_SERVICE);
        textViewLat = rootView.findViewById(R.id.textViewLat);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Log.d("Latitude", "Set Listener");
        } catch (Exception ex) {
            Log.d("Latitude", ex.getLocalizedMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        textViewLat = rootView.findViewById(R.id.textViewLat);
        textViewLat.setText("http://maps.google.com/maps?q=" + location.getLatitude() + "," + location.getLongitude());
        Linkify.addLinks(textViewLat, Linkify.WEB_URLS);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

}
