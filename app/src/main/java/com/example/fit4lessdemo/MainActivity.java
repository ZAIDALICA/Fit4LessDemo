package com.example.fit4lessdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String userName;
        super.onCreate(savedInstanceState);

        //checking if the user has logged in before or not
        if (SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_Email",MainActivity.this).length() == 0) {
            //send to main activity to log in
            //Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, SignIn.class);
            startActivity(i);
        } else {
            //get the user name
            userName = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_USER",MainActivity.this);
            Toast.makeText(getApplicationContext(), "Welcome Back "+userName, Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }

        //setContentView(R.layout.activity_main);


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
}