package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import android.app.Fragment;
public class SecondFragment extends Fragment {
    View view;
    Button SecondButton;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second, container, false);
        //Get the references of Button
        SecondButton = view.findViewById(R.id.secondButton);
        //Perform seOnClickListener on secondButton
        SecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display a massage by using a Toast
                Toast.makeText(getActivity(), "Second Fragment", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
