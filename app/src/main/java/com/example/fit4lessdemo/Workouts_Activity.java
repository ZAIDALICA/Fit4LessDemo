package com.example.fit4lessdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//for bottom navigation
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class Workouts_Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bNav;
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

        BottomNavigationView bNav = findViewById(R.id.nav_view);

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
            case "bNav" :
                bNav.setOnNavigationItemSelectedListener(this);
                break;
        }
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navigation_notifications:
                startActivity(new Intent(Workouts_Activity.this, Notification.class));
                return true;
            case R.id.navigation_bookings:
                startActivity(new Intent(Workouts_Activity.this, DeveloperPage.class));
                return true;
            case R.id.navigation_home:
                Intent home = new Intent( Workouts_Activity.this, NewsPage.class);
                startActivity(home);
                break;
            case R.id.navigation_dashboard:
                Intent map = new Intent( Workouts_Activity.this, Map.class);
                startActivity(map);
                break;
            /*case R.id.navigation_logout:
                logMeOutVoid();
                break;*/
        }
        return true;
    }
    /*
    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(Workouts_Activity.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(Workouts_Activity.this);
        startActivity(i);
    }
    */


}