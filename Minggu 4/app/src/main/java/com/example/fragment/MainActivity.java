package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    Button btnFirstFragment , btnSecondFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFirstFragment = (Button) findViewById(R.id.firstFragment);
        btnSecondFragment = (Button) findViewById(R.id.secondFragment);
        btnFirstFragment.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Load First Fragment
                loadFragment(new FirstFragment());
            }
        });
        btnSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load First Fragment
                loadFragment(new SecondFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment){
        //Create a FragmentManager
        FragmentManager fm = getFragmentManager();
        //Create a FragmentTransaction to begin the transaction and replace the fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //Replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); //Save the changes
    }
}