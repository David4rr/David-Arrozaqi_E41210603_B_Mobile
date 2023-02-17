package com.example.linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linear(View v) {
        Intent intent = new Intent(MainActivity.this, LinearLayout.class);
        startActivity(intent);
    }
    public void Relative(View v) {
        Intent intent = new Intent(MainActivity.this, RelativeLayout.class);
        startActivity(intent);
    }
    public void Constraint(View v) {
        Intent intent = new Intent(MainActivity.this, ConstraintLayout.class);
        startActivity(intent);
    }
    public void Frame(View v) {
        Intent intent = new Intent(MainActivity.this, FrameLayout.class);
        startActivity(intent);
    }
    public void Table(View v) {
        Intent intent = new Intent(MainActivity.this, TableLayout.class);
        startActivity(intent);
    }
    public void Material(View v) {
        Intent intent = new Intent(MainActivity.this, MaterialDesign.class);
        startActivity(intent);
    }
    public void ScrollV(View v) {
        Intent intent = new Intent(MainActivity.this, ScrollViewLayout.class);
        startActivity(intent);
    }
    public void ScrollVH(View v) {
        Intent intent = new Intent(MainActivity.this, ScrollView2Layout.class);
        startActivity(intent);
    }
}