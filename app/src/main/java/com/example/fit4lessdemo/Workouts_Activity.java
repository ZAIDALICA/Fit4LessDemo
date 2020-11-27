package com.example.fit4lessdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Workouts_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_);
        ImageView abs = findViewById(R.id.abs_wo);
        ImageView chest = findViewById(R.id.chest_wo);
        ImageView triceps = findViewById(R.id.triceps_wo);
        ImageView back = findViewById(R.id.back_wo);
        ImageView quads = findViewById(R.id.quads_wo);
        ImageView biceps = findViewById(R.id.bicep_wo);
        ImageView glutes = findViewById(R.id.glutes_wo);
        TextView which = findViewById(R.id.which_wo);

        String workout = WorkoutHelp.getWorkout();

        switch (workout) {
            case "abs":
                chest.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Abs);
                break;
            case "chest":
                abs.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Chest);
                break;
            case "triceps":
                abs.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Triceps);
                break;
            case "back":
                abs.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Back);
                break;
            case "quads":
                abs.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Quads);
                break;
            case "biceps":
                abs.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                glutes.setVisibility(View.INVISIBLE);
                which.setText(R.string.Biceps);
                break;
            case "glutes":
                abs.setVisibility(View.INVISIBLE);
                chest.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
                triceps.setVisibility(View.INVISIBLE);
                quads.setVisibility(View.INVISIBLE);
                biceps.setVisibility(View.INVISIBLE);
                which.setText(R.string.Glutes);
                break;
        }
    }
}