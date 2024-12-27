package com.example.quizeapk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView scoreText = findViewById(R.id.scoreText);
        Button homeButton = findViewById(R.id.homeButton);

        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        int total = intent.getIntExtra("TOTAL", 0);

        scoreText.setText("Your Score: " + score + "/" + total);

        homeButton.setOnClickListener(v -> finish());
    }
}
