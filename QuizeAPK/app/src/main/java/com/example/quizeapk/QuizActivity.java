package com.example.quizeapk;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button submitButton;

    private String[] questions = {
            "Quelle est la capitale culturelle du Maroc ?",
            "Quel est le plat traditionnel le plus connu du Maroc ?",
            "Quelle est la danse traditionnelle la plus célèbre du Maroc ?",
            "Quel est le style architectural traditionnel des médinas marocaines ?",
            "Quel festival de musique célèbre se déroule à Fès ?"
    };

    private String[][] options = {
            {"Fès", "Marrakech", "Rabat", "Casablanca"},
            {"Couscous", "Tajine", "Pastilla", "Harira"},
            {"Ahwach", "Ahidous", "Taskiwin", "Guedra"},
            {"Andalou", "Mauresque", "Arabo-musulman", "Berbère"},
            {"Festival de Fès des Musiques Sacrées", "Festival Mawazine", "Festival Gnaoua", "Festival du Désert"}
    };

    private int[] answers = {1, 2, 2, 3, 1}; // Correct answers: Fès, Tajine, Ahidous, Arabo-musulman, Festival de Fès des Musiques Sacrées // Correct answers
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        submitButton = findViewById(R.id.submitButton);

        loadQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionsGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an option!", Toast.LENGTH_SHORT).show();
                } else {
                    checkAnswer();
                }
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionsGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedOption = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId())) + 1;
        if (selectedOption == answers[currentQuestionIndex]) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            showScore();
        }
    }

    private void showScore() {
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questions.length);
        startActivity(intent);
        finish();
    }
}