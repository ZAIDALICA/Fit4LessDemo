package com.example.fit4lessdemo;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";

    public static String name = "";


    MyReceiver1 myReceiver1 = new MyReceiver1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //BroadcastReceiver broadcastReceiver = new BroadcastReceiver();
//        IntentFilter s_intentFilter = new IntentFilter();
//        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
//        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
//        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
//        registerReceiver(m_timeChangedReceiver, s_intentFilter);

        DBHelper helper = new DBHelper(this);

        //checking if the user has logged in before or not
        if (SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",MainActivity.this).length() == 0) {
            //send to main activity to log in
            //Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(this, SignIn.class);
//            startActivity(i);
            setContentView(R.layout.ask_user);

            Button btnLogin = (Button)findViewById(R.id.btn_yesLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignIn.class));
                }
            });

            Button btnRegister = (Button)findViewById(R.id.btn_noRegisterMe);
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Register.class));
                }
            });

            Button btnDeveloperMode = (Button)findViewById(R.id.btn_developerMode);
            btnDeveloperMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, DeveloperPage.class));
                }
            });

        } else {
            //get the user name
          
            String email = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",MainActivity.this);  //getting email from the sharedPreference
            String userName = helper.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, email);  //getting username from the database
            Toast.makeText(getApplicationContext(), "Welcome "+userName, Toast.LENGTH_SHORT).show();
            name = userName;
            startActivity(new Intent(MainActivity.this, NewsPage.class));

        }

        //setContentView(R.layout.activity_main);
    }


//    @Override
//    public boolean onCreateOptionMenue(Menu menu){
//        MenuInflater nMenueInflater = getMenuInflater();
//        nMenueInflater.inflate(R.menu.bottom_nav_menu,menu);
//        return true;
//    }

    //TODO this did not work for now but keep it for later
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //if the user clicked on the item then perform this action
//        if(item.getItemId() == R.id.navigation_logout){
//            //go to the login page again
//            Intent i = new Intent(MainActivity.this, SignIn.class);
//            String byUsername = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_USER",MainActivity.this);
//            Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
//            //clearing the user prefrences
//            //SaveUserLoginPreferences.clearUserLoginSharedPreferences(MainActivity.this);
//            startActivity(i);
//        }
//        return true;
//        //return super.onOptionsItemSelected(item);
//    }
    public static String getSavedName(){
        return name;
    }

    public void logMeOut(View v) {
        //go to the login page again
        Intent i = new Intent(MainActivity.this, SignIn.class);
        String byUsername = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_USER",MainActivity.this);
        Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT).show();
        //clearing the user prefrences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(MainActivity.this);
        startActivity(i);
    }

//    private final BroadcastReceiver m_timeChangedReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            final String action = intent.getAction();
//            Toast.makeText(context,"DATE OR TIME CHANGED",Toast.LENGTH_LONG).show();
//            if (action.equals(Intent.ACTION_TIME_CHANGED) ||
//                    action.equals(Intent.ACTION_TIMEZONE_CHANGED) ||
//                action.equals(Intent.ACTION_TIME_TICK) ||
//                    action.equals(Intent.ACTION_DATE_CHANGED)  ) {
//                Toast.makeText(context,"DATE OR TIME CHANGED",Toast.LENGTH_LONG).show();
//            }
//        }
//    };


//    @Override
//    protected void onStart() {
//        super.onStart();
//        //will be triggered when the app is in the foreground
//        IntentFilter s_intentFilter = new IntentFilter();
//        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
//        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
//        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
//        s_intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
//        registerReceiver(broadcastReceiver, s_intentFilter);
//    }


    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "actionString", Toast.LENGTH_LONG).show();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(myReceiver1, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(myReceiver1);
    }


    //    @Override
//    protected void onStop() {
//        super.onStop();
//
//        //will be triggered when the app is in the background
//        unregisterReceiver(m_timeChangedReceiver);
//    }



}