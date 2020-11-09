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
            userName = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_USER",MainActivity.this);
            Toast.makeText(getApplicationContext(), "Welcome Back "+userName, Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }

        //setContentView(R.layout.activity_main);
    }

}