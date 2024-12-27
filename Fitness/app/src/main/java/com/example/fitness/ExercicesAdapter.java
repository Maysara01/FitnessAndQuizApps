package com.example.fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

class ExerciseAdapter extends ArrayAdapter<Exercise> {
    public ExerciseAdapter(@NonNull Context context, @NonNull ArrayList<Exercise> exercises) {
        super(context, 0, exercises);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_item, parent, false);
        }

        Exercise exercise = getItem(position);

        ImageView exerciseImage = convertView.findViewById(R.id.exercise_image);
        TextView exerciseName = convertView.findViewById(R.id.exercise_name);

        exerciseImage.setImageResource(exercise.getImageResId());
        exerciseName.setText(exercise.getName());

        return convertView;
    }
}
