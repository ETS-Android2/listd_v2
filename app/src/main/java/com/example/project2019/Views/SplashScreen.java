package com.example.project2019.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.project2019.MainActivity;
import com.example.project2019.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int splash_time_out = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashScreenIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(splashScreenIntent);
                finish();
            }
        }, splash_time_out);
    }
}