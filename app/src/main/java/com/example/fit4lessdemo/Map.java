package com.example.fit4lessdemo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Map extends AppCompatActivity {
    private String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button confirm = findViewById(R.id.confirm_btn);
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
            //For now we will say:
            Toast.makeText(getApplicationContext(), "You have succesfully chosen: " + loc, Toast.LENGTH_LONG).show();
        }
    }
}