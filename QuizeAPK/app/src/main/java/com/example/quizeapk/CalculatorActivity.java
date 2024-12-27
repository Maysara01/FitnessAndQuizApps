package com.example.quizeapk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    private EditText firstNumber, secondNumber;
    private TextView result;
    private double currentResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize views
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        result = findViewById(R.id.result);

        // Initialize buttons
        Button btnBack = findViewById(R.id.btnBack);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnResult = findViewById(R.id.btnResult);
        Button btnReset = findViewById(R.id.btnReset);

        // Set click listeners
        btnBack.setOnClickListener(v -> finish());

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSub.setOnClickListener(v -> calculate('-'));
        btnMultiply.setOnClickListener(v -> calculate('*'));
        btnDivide.setOnClickListener(v -> calculate('/'));

        btnResult.setOnClickListener(v ->
                result.setText(String.format("RESULT: %.2f", currentResult)));

        btnReset.setOnClickListener(v -> {
            firstNumber.setText("");
            secondNumber.setText("");
            result.setText("RESULT");
            currentResult = 0;
        });
    }

    private void calculate(char operator) {
        try {
            double num1 = Double.parseDouble(firstNumber.getText().toString());
            double num2 = Double.parseDouble(secondNumber.getText().toString());

            switch (operator) {
                case '+':
                    currentResult = num1 + num2;
                    break;
                case '-':
                    currentResult = num1 - num2;
                    break;
                case '*':
                    currentResult = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero!",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    currentResult = num1 / num2;
                    break;
            }
            result.setText(String.format("RESULT: %.2f", currentResult));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
