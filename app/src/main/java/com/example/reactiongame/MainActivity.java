package com.example.reactiongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.singleButton).setOnClickListener(unused -> startActivity(
                new Intent(this, SingleGameActivity.class)));
        findViewById(R.id.multiButton).setOnClickListener(unused -> startActivity(
                new Intent(this, MultiGameActivity.class)));
    }
}
