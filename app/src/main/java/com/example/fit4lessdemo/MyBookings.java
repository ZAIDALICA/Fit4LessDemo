package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);



        //check the number of reservations already made, so make an if statement

        Button makeReservations = (Button)findViewById(R.id.makeReservation);
        //goes to next page to create reservations
        makeReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyBookings.this, CreateReservation.class));
            }
        });





    }
}