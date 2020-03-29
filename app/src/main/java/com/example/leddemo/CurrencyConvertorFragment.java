package com.example.leddemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CurrencyConvertorFragment extends Fragment {

   Context context;
   View rootView;

    EditText editTextDollarField;
    Button buttonConvert;
    public CurrencyConvertorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context =context;
    }

    void initViews()
    {
        buttonConvert =rootView.findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double inputDollar=   Double.parseDouble(editTextDollarField.getText().toString());
                Double outputPound = inputDollar * 0.65;
                Toast.makeText(context, outputPound.toString(), Toast.LENGTH_LONG).show();
            }
        });

        editTextDollarField = rootView.findViewById(R.id.editTextDollarField);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_currency_convertor, container, false);
        initViews();
        return rootView;
    }
}
