package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MyBookings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
    }
    //goes to next page to create reservations
    public void makeReservation(View v){
        Intent i = new Intent(this, CreateReservation.class);
        startActivity(i);
    }
    /*
    //Displays the database of reservations
    public void listReservations(AdapterView<?> parent, View view, int position, long id) {
          //database
    }
    */
    //check the number of reservations already made, so make an if statement

}