package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class NewsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        WebView myWebView = (WebView) findViewById(R.id.news);//these 4 lines are required to get internet pages to show.
        myWebView.loadUrl("https://www.fit4less.ca/faq/club-changes");//If error persists, uninstall the app on the VD and then re-run program to reinstall
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Button dev = findViewById(R.id.devButton);
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsPage.this, Map.class));
            }
        });

    }
    public void logMeOut(View v) {
        //go to the login page again
        Intent i = new Intent(NewsPage.this, SignIn.class);
        String byUsername = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_USER",NewsPage.this);
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user prefrences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(NewsPage.this);
        startActivity(i);
    }

}