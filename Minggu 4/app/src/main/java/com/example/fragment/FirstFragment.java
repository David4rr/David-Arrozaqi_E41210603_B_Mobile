package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;
import androidx.annotation.Nullable;

public class FirstFragment extends Fragment{
    View view;
    Button firstButton;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);
        //Get the references of Button
        firstButton = view.findViewById(R.id.firstButton);
        //Perform seOnClickListener on secondButton
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display a massage by using a Toast
                Toast.makeText(getActivity(), "Fisrt Fragment", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
