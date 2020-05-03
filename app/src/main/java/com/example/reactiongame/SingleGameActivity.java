package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import timerx.Stopwatch;
import timerx.StopwatchBuilder;
import timerx.Timer;
import timerx.TimerBuilder;

public class SingleGameActivity extends AppCompatActivity {

    /**
     * stores a stopwatch to keep track of reaction time
     */
    private Stopwatch stopwatch;

    /**
     * stores a timer to be used in the random waiting time
     */
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        findViewById(R.id.redButton).setOnClickListener(unused -> dontPress());
        // Declare new stopwatch object
        stopwatch = new StopwatchBuilder()
        // Set start format of time
        .startFormat("SS.LL")
        .build();
        buildTimer();
    }


    /**
     * runs if screen is tapped too early. prompts user to restart the game
     */
    private void dontPress() {
        findViewById(R.id.redButton).setVisibility(View.GONE);
        findViewById(R.id.tooEarly).setVisibility(View.VISIBLE);
        timer.reset();
        findViewById(R.id.tooEarly).setOnClickListener(unused -> startAgain());
    }

    /**
     * builds a timer to randomly set amount of time player has to wait before they can react
     */
    private void buildTimer() {
        Random random = new Random();
        int number = random.nextInt(7000) + 3000;

        timer = new TimerBuilder()
                // Set start time
                .startTime(number, TimeUnit.MILLISECONDS)
                // Set start format of time
                .startFormat("SS.LL")
                // Executing action
                .actionWhen(0, TimeUnit.MILLISECONDS, () -> {
                    findViewById(R.id.redButton).setVisibility(View.GONE);
                    findViewById(R.id.tooEarly).setVisibility(View.GONE);
                    findViewById(R.id.greenButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.greenButton).setOnClickListener(unused -> greenButtonPress());
                    // Start stopwatch
                    stopwatch.start();

                })
                .build();
        timer.start();
    }

    /**
     * resets the game if player wants to play again
     */
    private void startAgain() {
        findViewById(R.id.redButton).setVisibility(View.VISIBLE);
        findViewById(R.id.tooEarly).setVisibility(View.GONE);
        findViewById(R.id.greenButton).setVisibility(View.GONE);
        findViewById(R.id.redButton).setOnClickListener(unused -> dontPress());
        buildTimer();
    }

    /**
     * once user reacts correctly, displays win screen with time and button to play again
     */
    private void greenButtonPress() {
        stopwatch.stop();
        long timeTo = stopwatch.getTimeIn(TimeUnit.MILLISECONDS);
        stopwatch.reset();
        findViewById(R.id.redButton).setVisibility(View.GONE);
        findViewById(R.id.tooEarly).setVisibility(View.GONE);
        findViewById(R.id.greenButton).setVisibility(View.GONE);
        TextView text = (TextView) findViewById(R.id.winTime);
        text.setText("Your Time: " + timeTo + "ms");
        findViewById(R.id.winTime).setVisibility(View.VISIBLE);
        findViewById(R.id.playAgain).setVisibility(View.VISIBLE);
        findViewById(R.id.playAgain).setOnClickListener(unused -> startAgain());
    }

}
