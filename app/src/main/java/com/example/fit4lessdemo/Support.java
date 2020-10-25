package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Intent i = getIntent(); //getting the intent associated with this activity
        String message = i.getStringExtra("name").toString();  //getting the text
        if (message  == null){
            message  = "whatever";
        }
        else {
            ((TextView)findViewById(R.id.destination)).setText(message);  //setting the text into destination
        }
    }
}