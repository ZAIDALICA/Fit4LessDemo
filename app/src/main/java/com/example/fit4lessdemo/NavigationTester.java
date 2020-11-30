package com.example.fit4lessdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavigationTester extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_tester);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById((R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
                case R.id.workout:
                    Intent workout = new Intent( NavigationTester.this, WorkoutHelp.class);
                    startActivity(workout);
                    break;
                case R.id.listReservation:
                    //bookings();
                    Intent reservation = new Intent( NavigationTester.this, MyBookings.class);
                    startActivity(reservation);
                    break;
                case R.id.map:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapsFragment()).commit();
                    break;
                case R.id.nav_logout:
                    logMeOutVoid();
                    break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        { super.onBackPressed(); }
    }


    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(NavigationTester.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(NavigationTester.this);
        startActivity(i);
    }
    public void bookings(){
        //Intent aboutIntent = new Intent(this, ProductListActivity.class);
        Intent bookings = new Intent(NavigationTester.this, DeveloperPage.class);
        startActivity(bookings);
    }
}
