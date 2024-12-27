package com.example.fitness.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitness.Exercise;
import com.example.fitness.R;

import java.util.ArrayList;

public class ExercicesAdapter extends ArrayAdapter<Exercise> {
    public ExercicesAdapter(@NonNull Context context, @NonNull ArrayList<Exercise> exercises) {
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
