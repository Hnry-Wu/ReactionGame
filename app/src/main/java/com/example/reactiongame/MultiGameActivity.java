package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import timerx.Stopwatch;
import timerx.StopwatchBuilder;
import timerx.Timer;
import timerx.TimerBuilder;

public class MultiGameActivity extends AppCompatActivity {

    /**
     * stores a timer to be used in the random waiting time
     */
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        findViewById(R.id.redButton1).setVisibility(View.VISIBLE);
        findViewById(R.id.tooEarly1).setVisibility(View.GONE);
        findViewById(R.id.greenButton1).setVisibility(View.GONE);
        findViewById(R.id.redButton2).setVisibility(View.VISIBLE);
        findViewById(R.id.tooEarly2).setVisibility(View.GONE);
        findViewById(R.id.greenButton2).setVisibility(View.GONE);
        findViewById(R.id.winner1).setVisibility(View.GONE);
        findViewById(R.id.winner2).setVisibility(View.GONE);

        findViewById(R.id.redButton1).setOnClickListener(unused -> dontPress1());
        findViewById(R.id.redButton2).setOnClickListener(unused -> dontPress2());

        buildTimer();
    }

    /**
     * runs if screen is tapped too early. prompts users to restart the game
     */
    private void dontPress1() {
        findViewById(R.id.redButton1).setVisibility(View.GONE);
        findViewById(R.id.tooEarly1).setVisibility(View.VISIBLE);
        timer.stop();
        findViewById(R.id.tooEarly1).setOnClickListener(unused -> startAgain());
    }

    private void dontPress2() {
        findViewById(R.id.redButton2).setVisibility(View.GONE);
        findViewById(R.id.tooEarly2).setVisibility(View.VISIBLE);
        timer.stop();
        findViewById(R.id.tooEarly2).setOnClickListener(unused -> startAgain());
    }

    /**
     * builds a timer to randomly set amount of time players have to wait before they can react
     */
    private void buildTimer() {
        Random random = new Random();
        int number = random.nextInt(3000) + 2000;

        timer = new TimerBuilder()
                // Set start time
                .startTime(number, TimeUnit.MILLISECONDS)
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

                    findViewById(R.id.greenButton1).setOnClickListener(unused -> greenButtonPress1());
                    findViewById(R.id.greenButton2).setOnClickListener(unused -> greenButtonPress2());

                })
                .build();
        timer.start();
    }

    /**
     * resets the game if players wants to play again
     */
    private void startAgain() {
        findViewById(R.id.redButton1).setVisibility(View.VISIBLE);
        findViewById(R.id.tooEarly1).setVisibility(View.GONE);
        findViewById(R.id.greenButton1).setVisibility(View.GONE);
        findViewById(R.id.redButton2).setVisibility(View.VISIBLE);
        findViewById(R.id.tooEarly2).setVisibility(View.GONE);
        findViewById(R.id.greenButton2).setVisibility(View.GONE);
        findViewById(R.id.winner1).setVisibility(View.GONE);
        findViewById(R.id.winner2).setVisibility(View.GONE);

        findViewById(R.id.redButton1).setOnClickListener(unused -> dontPress1());
        findViewById(R.id.redButton2).setOnClickListener(unused -> dontPress2());
        buildTimer();
    }

    /**
     * once first user reacts correctly, displays which user won and allows users to play again
     */
    private void greenButtonPress1() {

        findViewById(R.id.redButton1).setVisibility(View.GONE);
        findViewById(R.id.tooEarly1).setVisibility(View.GONE);
        findViewById(R.id.greenButton1).setVisibility(View.GONE);
        findViewById(R.id.redButton2).setVisibility(View.GONE);
        findViewById(R.id.tooEarly2).setVisibility(View.GONE);
        findViewById(R.id.greenButton2).setVisibility(View.GONE);

        findViewById(R.id.winner1).setVisibility(View.VISIBLE);
        findViewById(R.id.winner1).setOnClickListener(unused -> startAgain());
    }

    private void greenButtonPress2() {

        findViewById(R.id.redButton1).setVisibility(View.GONE);
        findViewById(R.id.tooEarly1).setVisibility(View.GONE);
        findViewById(R.id.greenButton1).setVisibility(View.GONE);
        findViewById(R.id.redButton2).setVisibility(View.GONE);
        findViewById(R.id.tooEarly2).setVisibility(View.GONE);
        findViewById(R.id.greenButton2).setVisibility(View.GONE);

        findViewById(R.id.winner2).setVisibility(View.VISIBLE);
        findViewById(R.id.winner2).setOnClickListener(unused -> startAgain());
    }

}
