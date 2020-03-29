package com.example.leddemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.leddemo.model.FlashlightProvider;


public class FlashlightFragment extends Fragment {
    private FlashlightProvider flashlightProvider;
    private boolean isFlashOn = false;
    private Button buttonLed;
    private Context contextParent;
    private View rootView;

    public FlashlightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_flashlight, container, false);
        initLed();
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextParent =context;
    }

    private void initLed() {
        flashlightProvider = new FlashlightProvider(contextParent);

        buttonLed = rootView.findViewById(R.id.buttonLed);
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
}
