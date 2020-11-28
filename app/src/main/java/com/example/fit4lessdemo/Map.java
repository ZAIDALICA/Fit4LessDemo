package com.example.fit4lessdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Map extends AppCompatActivity {
    private String loc;
    BottomNavigationView bNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button confirm = findViewById(R.id.confirm_btn);
        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(Map.this, NewsPage.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                }
                return false;
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loc = MapsFragment.getLocation();
                confirmLocation();
            }
        });
    }

    private void confirmLocation(){
        if (loc.equals("")){
            Toast.makeText(getApplicationContext(),"Please Select a Gym",Toast.LENGTH_LONG).show();
        }else {
            //TODO Store loc in the database here
            //TODO Send the user to booking
            Intent i = new Intent(Map.this, Bookings.class);
            i.putExtra("location", loc);
            //Log.d("GGGGGeorge", loc);
            //For now we will say:
            //Toast.makeText(getApplicationContext(), "You have succesfully chosen: " + loc, Toast.LENGTH_LONG).show();
            startActivity(i);
        }
    }
    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(Map.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(Map.this);
        startActivity(i);
    }
}