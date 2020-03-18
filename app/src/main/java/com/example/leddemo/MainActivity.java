package com.example.leddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FlashlightProvider flashlightProvider;
    boolean isFlashOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashlightProvider = new FlashlightProvider(this);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (isFlashOn) {
            flashlightProvider.turnFlashlightOff();
            isFlashOn = false;
        } else {
            flashlightProvider.turnFlashlightOn();
            isFlashOn = true;
        }
    }
}
