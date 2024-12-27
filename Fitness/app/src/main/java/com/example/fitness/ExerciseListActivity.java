package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitness.ui.login.ExercicesAdapter;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        ListView listView = findViewById(R.id.exercise_list);
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Mountain Climber", R.drawable.img));
        exercises.add(new Exercise("Abdominal Crunch", R.drawable.abdominal_crunch));
        exercises.add(new Exercise("Squat Jump", R.drawable.squat_jump));
        exercises.add(new Exercise("Russian Twist", R.drawable.russian_twist));
        exercises.add(new Exercise("Push up from knees", R.drawable.push_up_from_knees));
        exercises.add(new Exercise("Push up one hand", R.drawable.push_up_one_hand));

        ExercicesAdapter adapter = new ExercicesAdapter(this, exercises);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            Exercise selectedExercise = exercises.get(position);
            Intent intent = new Intent(ExerciseListActivity.this, DetailActivity.class);
            intent.putExtra("exerciseName", selectedExercise.getName());
            intent.putExtra("exerciseImage", selectedExercise.getImageResId());
            startActivity(intent);
        });
    }
}
