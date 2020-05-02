package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import timerx.Timer;
import timerx.TimerBuilder;

public class MultiGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        findViewById(R.id.redButton1).setOnClickListener(unused -> dontPress());



        Timer timer = new TimerBuilder()
        // Set start time
        .startTime(5000, TimeUnit.MILLISECONDS)
        // Set start format of time
        .startFormat("SS.LL")
        // Executing action
        .actionWhen(0, TimeUnit.MILLISECONDS, () -> {
            findViewById(R.id.redButton1).setVisibility(View.GONE);
            findViewById(R.id.tooEarly1).setVisibility(View.GONE);
            findViewById(R.id.greenButton1).setVisibility(View.VISIBLE);
            findViewById(R.id.redButton2).setVisibility(View.GONE);
            findViewById(R.id.tooEarly2).setVisibility(View.GONE);
            findViewById(R.id.greenButton2).setVisibility(View.VISIBLE);
            // Start stopwatch
            //stopwatch.start();
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
