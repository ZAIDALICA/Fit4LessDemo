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

public class NewsPage extends AppCompatActivity {
    BottomNavigationView bNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_dashboard:
                        startActivity(new Intent(NewsPage.this, Map.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                    case R.id.navigation_bookings:
                        bookings();

                }
                return false;
            }
        });

        WebView myWebView = (WebView) findViewById(R.id.news);//these 4 lines are required to get internet pages to show.
        myWebView.loadUrl("https://www.fit4less.ca/faq/club-changes");//If error persists, uninstall the app on the VD and then re-run program to reinstall
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

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

}