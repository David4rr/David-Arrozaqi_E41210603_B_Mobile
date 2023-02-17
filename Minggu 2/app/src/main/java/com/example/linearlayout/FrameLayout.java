package com.example.linearlayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
public class FrameLayout extends AppCompatActivity{
    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btn1);
    }
}
