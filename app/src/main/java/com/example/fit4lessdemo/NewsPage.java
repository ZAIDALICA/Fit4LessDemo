package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NewsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        WebView myWebView = (WebView) findViewById(R.id.news);//these 4 lines are required to get internet pages to show.
        myWebView.loadUrl("https://www.fit4less.ca/faq/club-changes");//If error persists, uninstall the app on the VD and then re-run program to reinstall
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

}