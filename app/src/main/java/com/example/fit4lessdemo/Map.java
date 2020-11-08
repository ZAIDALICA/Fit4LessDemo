package com.example.fit4lessdemo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Map extends AppCompatActivity {
    private String title = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        WebView myWebV = (WebView) findViewById(R.id.map);
        myWebV.loadUrl("https://www.google.com/maps/search/fit4less/@43.7229945,-79.6024431,11z/data=!3m1!4b1");
        WebSettings webSettings = myWebV.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (myWebV.performClick()){
            title = myWebV.getTitle();
            Toast.makeText(getApplicationContext(),title,Toast.LENGTH_LONG).show();
        }
    }
}