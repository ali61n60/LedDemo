package com.example.leddemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//TODO test this fragment performance in an Activity
public class AnimationFragment extends Fragment {

    Context context;
    View rootView;

    private ImageView _imageViewBart;
    private ImageView _imageViewHomer;


    public AnimationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context =context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_animation, container, false);
        initViews();
        return rootView;
    }

    void initViews() {
        _imageViewBart =rootView.findViewById(R.id.imageViewBart);
        _imageViewBart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageViewBart.animate().alpha(0f).setDuration(2000);
                _imageViewHomer.animate().alpha(1.0f).setDuration(2000);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    _imageViewBart.setTranslationZ(0);
                    _imageViewHomer.setTranslationZ (10);
                }

            }
        }) ;

        _imageViewHomer =rootView.findViewById(R.id.imageViewHomer);
        _imageViewHomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _imageViewBart.animate().alpha(1.0f).setDuration(2000);
                _imageViewHomer.animate().alpha(0f).setDuration(2000);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    _imageViewBart.setTranslationZ(10);
                    _imageViewHomer.setTranslationZ (0);
                }
            }
        }) ;
    }
}
