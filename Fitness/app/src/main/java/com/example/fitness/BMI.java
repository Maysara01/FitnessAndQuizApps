package com.example.fitness;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMI extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        tvBMI = findViewById(R.id.tvBMI);
        Button btnCalculateBMI = findViewById(R.id.btnCalculateBMI);
        Button btnBMIChart = findViewById(R.id.btnBMIChart);

        // Button to calculate BMI
        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = etHeight.getText().toString();
                String weightStr = etWeight.getText().toString();

                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    Toast.makeText(BMI.this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    float height = Float.parseFloat(heightStr) / 100; // Convert CM to M
                    float weight = Float.parseFloat(weightStr);
                    float bmi = weight / (height * height);

                    tvBMI.setText(String.format("Your BMI is: %.2f", bmi));
                } catch (NumberFormatException e) {
                    Toast.makeText(BMI.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button to view BMI Chart
        btnBMIChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to navigate to BMI Chart (optional)
                Toast.makeText(BMI.this, "BMI Chart coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
