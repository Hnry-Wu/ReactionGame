package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import timerx.Timer;
import timerx.TimerBuilder;

public class SingleGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        findViewById(R.id.redButton).setOnClickListener(unused -> dontPress());
        Random random = new Random();
        int number = random.nextInt(3000) + 2000;

        Timer timer = new TimerBuilder()
        // Set start time
        .startTime(number, TimeUnit.MILLISECONDS)
        // Set start format of time
        .actionWhen(0, TimeUnit.MILLISECONDS, () -> {
            findViewById(R.id.redButton).setVisibility(View.GONE);
            findViewById(R.id.greenButton).setVisibility(View.VISIBLE);
        })
        .build();

        timer.start();

    }


    private void dontPress() {
        findViewById(R.id.redButton).setVisibility(View.GONE);
        findViewById(R.id.tooEarly).setVisibility(View.VISIBLE);
        //findViewById(R.id.tooEarly).setOnClickListener(unused -> onCreate())
    }



}
