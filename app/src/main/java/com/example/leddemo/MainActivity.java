package com.example.leddemo;

import android.content.res.Configuration;
import android.os.Bundle;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // changeLocaleTo("fa");

        setContentView(R.layout.activity_main);

        addFlashlightFragment();
        addGpsFragment();
        addCurrencyFragmeny();
    }

    private void addCurrencyFragmeny() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.currencyContainer,new CurrencyConvertorFragment())
                .commit();
    }

    private void addFlashlightFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flashlightContainer, new FlashlightFragment())
                .commit();
    }
    private void addGpsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.gpsContainer, new GpsFragment())
                .commit();
    }

    private void changeLocaleTo(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

}
