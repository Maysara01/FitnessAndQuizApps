package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Make sure this layout file exists

        // Initialize Buttons
        Button btnStart = findViewById(R.id.btnStart);
        Button btnBMI = findViewById(R.id.btnBMI);
        Button btnAlarme = findViewById(R.id.btnAlarme);
        Button btnSetting = findViewById(R.id.btnSetting);

        // Start button logic (example: navigate to another activity)
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, navigate to a "Start" Activity
                Intent intent = new Intent(Home.this, ExerciseListActivity.class);
                startActivity(intent);
            }
        });

        // BMI button logic (example: navigate to a BMI calculation activity)
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, navigate to a "BMI" Activity
                Intent intent = new Intent(Home.this, BMI.class);
                startActivity(intent);
            }
        });

        // Calendar button logic (example: navigate to a calendar activity)
        btnAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, navigate to a "Alarme" Activity
                Intent intent = new Intent(Home.this, Alarm_setting.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, navigate to a "Alarme" Activity
                Intent intent = new Intent(Home.this, Setting.class);
                startActivity(intent);
            }
        });


    }
}
