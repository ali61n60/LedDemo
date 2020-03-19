package com.example.leddemo;

import android.content.Context;
import android.location.LocationManager;

public class LocationProvider {
    Context context;
    public LocationProvider(Context context){
        this.context=context;
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void GetLocation(){

    }


}
