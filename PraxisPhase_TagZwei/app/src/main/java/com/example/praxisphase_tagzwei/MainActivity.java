package com.example.praxisphase_tagzwei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private PongView pongView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pongView = findViewById(R.id.pongPlaceHolder);
        setContentView(R.layout.activity_main);
    }
}