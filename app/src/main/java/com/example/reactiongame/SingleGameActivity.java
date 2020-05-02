package com.example.reactiongame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SingleGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        findViewById(R.id.redButton).setOnClickListener(unused -> dontPress());

    }


    private void dontPress() {
        findViewById(R.id.redButton).setVisibility(View.GONE);
        findViewById(R.id.tooEarly).setVisibility(View.VISIBLE);
        //findViewById(R.id.tooEarly).setOnClickListener(unused -> onCreate())

    }



}
