package com.example.fit4lessdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NewsPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        //webview
        WebView myWebView = (WebView) findViewById(R.id.news);//these 4 lines are required to get internet pages to show.
        myWebView.loadUrl("https://www.fit4less.ca/faq/club-changes");//If error persists, uninstall the app on the VD and then re-run program to reinstall
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Julia: code for drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById((R.id.nav_view));
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(NewsPage.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(NewsPage.this);
        startActivity(i);
    }

    public void bookings(){
        //Intent aboutIntent = new Intent(this, ProductListActivity.class);
        Intent bookings = new Intent(this, DeveloperPage.class);  //TODO for now it will go to the developer mode later it will show the bookings system
        startActivity(bookings);
    }

    //code for drawer
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.notifications:
                Intent notification = new Intent( NewsPage.this, Notification.class);
                startActivity(notification);
                break;
            case R.id.home:
                Intent home = new Intent( NewsPage.this, DeveloperPage.class);
                startActivity(home);
                break;
            case R.id.listReservation:
                //bookings();
                Intent reservation = new Intent( NewsPage.this, MyBookings.class);
                startActivity(reservation);
                break;
            case R.id.makeReservation:
                Intent makeReservation = new Intent( NewsPage.this, Bookings.class);
                startActivity(makeReservation);
                break;
            case R.id.workout:
                Intent workout = new Intent( NewsPage.this, WorkoutHelp.class);
                startActivity(workout);
                break;
            case R.id.map:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapsFragment()).commit();
                // if we start the map as a fragment we will not be able to see the confirm button
                Intent map = new Intent( NewsPage.this, Map.class);
                startActivity(map);
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
//        else
//        { super.onBackPressed(); }  you can't go back in the main page
    }

}