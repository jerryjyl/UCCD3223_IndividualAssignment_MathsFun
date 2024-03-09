package com.example.mathsfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    // Variable declarations
    private static int SPLASH_SCREEN = 5000;
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;
    TextView appName;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hooks
        lottieAnimationView = findViewById(R.id.lottie);
        appName = findViewById(R.id.appname);
        progressBar = findViewById(R.id.progressbar);

        // Animations
        lottieAnimationView.animate().translationY(1400).setDuration(1800).setStartDelay(4000);
        appName.animate().translationY(1400).setDuration(1800).setStartDelay(4000);
        progressBar.animate().translationY(1400).setDuration(1800).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

        progress();

    }

    public void progress(){
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);

                if (counter == 100) t.cancel();
            }
        };

        t.schedule(tt, 0, 30);
    }
}