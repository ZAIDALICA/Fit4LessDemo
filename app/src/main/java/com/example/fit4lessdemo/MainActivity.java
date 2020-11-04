package com.example.fit4lessdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    String text = "";
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.textView);
//
//        DatabaseHelper db = new DatabaseHelper(this);
//
//        //inserting gym members
//       db.addGymMember(new GymMember("George", "1234567"));
//        db.addGymMember(new GymMember("Matt", "7654321"));
//        db.addGymMember(new GymMember("Mujtabah", "43333333"));
//        db.addGymMember(new GymMember("Julia", "33333334"));
//        db.addGymMember(new GymMember("Suhail", "7777777"));
//
//        //reading and displaying all gymMembers
//        List<GymMember> gymMembers = db.getAllGymMembers();
//
//        for(GymMember g : gymMembers){
//            String log = "ID: " + g.getId() + ", NAME: " + g.getName() + ", NUMBER: " + g.getPhone_number() + "\n";
//           text = text + log;
//        }
//
//        textView.setText(text);



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

    public void goToDb(View v){
        Intent i = new Intent(this, dbTest.class);
        startActivity(i);
    }
}