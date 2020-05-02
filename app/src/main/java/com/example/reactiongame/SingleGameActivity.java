package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;
//import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import timerx.Stopwatch;
import timerx.StopwatchBuilder;
import timerx.Timer;
import timerx.TimerBuilder;

public class SingleGameActivity extends AppCompatActivity {
    private Stopwatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        findViewById(R.id.redButton).setOnClickListener(unused -> dontPress());

        stopwatch = new StopwatchBuilder()
        // Set start format of time
        .startFormat("SS.LL")
        // When time is equal to one hour, change format to "HH:MM:SS"
        .build();


        Random random = new Random();
        //int number = random.nextInt(3000) + 2000;

        Timer timer = new TimerBuilder()
        // Set start time
        .startTime(5000, TimeUnit.MILLISECONDS)
        // Set start format of time
        .startFormat("SS.LL")
        // Executing action
        .actionWhen(0, TimeUnit.MILLISECONDS, () -> {
            findViewById(R.id.redButton).setVisibility(View.GONE);
            findViewById(R.id.tooEarly).setVisibility(View.GONE);
            findViewById(R.id.greenButton).setVisibility(View.VISIBLE);
            // Start stopwatch
            stopwatch.start();

        })
        .build();

        timer.start();


    }


    private void dontPress() {
        findViewById(R.id.redButton).setVisibility(View.GONE);
        findViewById(R.id.tooEarly).setVisibility(View.VISIBLE);

    }

    private void greenButtonPress() {
        stopwatch.stop();
        long timeTo = stopwatch.getTimeIn(TimeUnit.MILLISECONDS);

    }



}
