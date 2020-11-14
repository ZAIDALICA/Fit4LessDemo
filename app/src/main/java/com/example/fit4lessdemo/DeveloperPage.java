package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeveloperPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);
    }

    public void sendEmail(View v){
        Intent i = new Intent(this, Support.class);
        String message = ((Button)v).getText().toString();
        //now we need to transfer the message into the support activity via the intent i
        i.putExtra("name", message);  //we loaded the message with the id name
        startActivity(i);  //we start the activity
        //now go the support activity and get the message there

    }
    public void sendSignIn(View v){
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }
    public void sendRegister(View v){
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
    public void sendNews(View v){
        Intent i = new Intent(this, NewsPage.class);
        startActivity(i);
    }
    public void sendMuscles(View v){
        Intent i = new Intent(this, WorkoutHelp.class);
        startActivity(i);
    }
    public void sendMap(View v){
        Intent i = new Intent(this, Map.class);
        startActivity(i);
    }

    public void goToDb(View v){
        Intent i = new Intent(this, dbTest.class);
        startActivity(i);
    }

    public void booking(View v){
        Intent i = new Intent(this, CreateReservation.class);
        startActivity(i);
    }


}