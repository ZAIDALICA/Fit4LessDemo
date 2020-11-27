package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class WorkoutHelp extends AppCompatActivity {

    private static String workout = "";
    private static final String[] exercises = {"abs", "quads","triceps","biceps","chest","back","glutes"};
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_help);
        ImageView abs = findViewById(R.id.abs);
        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "abs";
                toWorkouts();
            }
        });
        ImageView quads = findViewById(R.id.quads);
        quads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "quads";
                toWorkouts();
            }
        });
        ImageView triceps = findViewById(R.id.triceps);
        triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "triceps";
                toWorkouts();
            }
        });
        ImageView biceps = findViewById(R.id.biceps);
        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "biceps";
                toWorkouts();
            }
        });
        ImageView chest = findViewById(R.id.chest);
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "chest";
                toWorkouts();
            }
        });
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "back";
                toWorkouts();
            }
        });
        ImageView glutes = findViewById(R.id.glutes);
        glutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = "glutes";
                toWorkouts();
            }
        });
        Button rand = findViewById(R.id.randomButton);
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout = exercises[rng.nextInt(7)];
                toWorkouts();
            }
        });
    }
    public void toWorkouts(){
        startActivity(new Intent(WorkoutHelp.this, Workouts_Activity.class));
    }
    public static String getWorkout(){
        return workout;
    }
}