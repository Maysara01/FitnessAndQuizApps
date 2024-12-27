package com.example.quizeapk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnMediaPlayer, btnCalculator, btnCamera, btnQuizz;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des boutons
        btnMediaPlayer = findViewById(R.id.btnMediaPlayer);
        btnCalculator = findViewById(R.id.btnCalculator);
        btnCamera = findViewById(R.id.btnCamera);
        btnQuizz = findViewById(R.id.btnQuizz);
        btnBack = findViewById(R.id.btnBack);

        // Gestionnaire de clic pour le bouton Media Player
        btnMediaPlayer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
            startActivity(intent);
        });

        // Gestionnaire de clic pour le bouton Calculator
        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        // Gestionnaire de clic pour le bouton Camera
        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });

        // Gestionnaire de clic pour le bouton Quizz
        btnQuizz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        // Gestionnaire de clic pour le bouton Back
        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}