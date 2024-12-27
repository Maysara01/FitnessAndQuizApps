package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton, resetButton;
    private long timeLeftInMillis = 60000; // 1 minute
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView exerciseImage = findViewById(R.id.detail_image);
        TextView exerciseName = findViewById(R.id.detail_name);
        timerText = findViewById(R.id.detail_timer);
        startButton = findViewById(R.id.button_start);
        resetButton = findViewById(R.id.button_reset);

        Intent intent = getIntent();
        String name = intent.getStringExtra("exerciseName");
        int imageRes = intent.getIntExtra("exerciseImage", -1);

        exerciseName.setText(name);
        exerciseImage.setImageResource(imageRes);

        startButton.setOnClickListener(v -> startTimer());
        resetButton.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Timer terminé
            }
        }.start();
    }

    private void resetTimer() {
        timeLeftInMillis = 60000; // 1 minute
        updateTimer();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        timerText.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
