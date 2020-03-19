package com.example.leddemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    public static final int AccessFineLocationRequestCode = 1;
    FlashlightProvider flashlightProvider;
    boolean isFlashOn = false;
    Button buttonLed;

    TextView textViewLat;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeLocaleTo("fa");

        setContentView(R.layout.activity_main);

        initLed();
        initGps();

    }

    private void initLed() {
        flashlightProvider = new FlashlightProvider(this);

        buttonLed = findViewById(R.id.buttonLed);
        buttonLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    flashlightProvider.turnFlashlightOff();
                    isFlashOn = false;
                } else {
                    flashlightProvider.turnFlashlightOn();
                    isFlashOn = true;
                }
            }
        });
    }


    private void initGps() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            List<String> listPermissionsNeeded = new ArrayList<>();
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions(MainActivity.this,  listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), AccessFineLocationRequestCode);
            Log.d("Latitude", "Asking for Permission");
            return;

        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        textViewLat = findViewById(R.id.textViewLat);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Log.d("Latitude", "Set Listener");
        } catch (Exception ex) {
            Log.d("Latitude", ex.getLocalizedMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == AccessFineLocationRequestCode) {
            initGps();
        }

    }

    private void changeLocaleTo(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onLocationChanged(Location location) {
        textViewLat = (TextView) findViewById(R.id.textViewLat);
        textViewLat.setText("http://maps.google.com/maps?q="+location.getLatitude()+"," +  location.getLongitude());
        Linkify.addLinks(textViewLat,Linkify.WEB_URLS);

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
