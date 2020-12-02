package com.example.fit4lessdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;


public class Map extends AppCompatActivity {
    private Toast mToast = null;
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    private String loc;

    DBBookingsHandler dbBookingsHandler;
    BottomNavigationView bNav; //todo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button confirm = findViewById(R.id.confirm_btn);
        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(Map.this, NewsPage.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                }
                return false;
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoc(MapsFragment.getLocation());
                confirmLocation();
            }
        });
    }

    private void confirmLocation(){
        Intent appointmentIntent = getIntent();

        //String service = "";
        //String staff = "";
        String date = "";
        //String time = "";
        try {
            //service = appointmentIntent.getExtras().getString("service");
            //staff = appointmentIntent.getExtras().getString("staff");
            date = appointmentIntent.getExtras().getString("date");
            //time = appointmentIntent.getExtras().getString("time");
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        //Log.d("Extrasaaaaaas", service + " "+ staff);
        if (getLoc().equals("")){
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(getApplicationContext(),"Please Select a Gym",Toast.LENGTH_LONG);
            mToast.show();

        }else {
            //TODO Store loc in the database here
            //TODO Send the user to booking
            Intent i = new Intent(Map.this, Appointment.class);
            i.putExtra("location", getLoc());

            //Log.d("Extrasaaaaaas22222222", service + " "+ staff);
            if (date != null && !date.equals("")){
                //i.putExtra("serviceM", service);
               // i.putExtra("staffM", staff);
                i.putExtra("dateM",  date);
                //i.putExtra("timeM", time);
            }
            //Log.d("GGGGGeorge", loc);
            //For now we will say:
            //Toast.makeText(getApplicationContext(), "You have succesfully chosen: " + loc, Toast.LENGTH_LONG).show();
            dbBookingsHandler = new DBBookingsHandler(Map.this);
            Calendar x = Calendar.getInstance();
            int day = x.get(Calendar.DAY_OF_MONTH);
            int month = x.get(Calendar.MONTH);
            int year = x.get(Calendar.YEAR);
            String date1 = String.valueOf(month + 1) + "/" + String.valueOf(day)  + "/" + String.valueOf(year);
            Log.d("date", date1 + " location"+getLoc());
            String reservationToday = dbBookingsHandler.dbCount(getLoc(),date1,false);

            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(getApplicationContext(), "You have selected: " + getLoc()+ "  | Availability today "+reservationToday+"/10" ,Toast.LENGTH_LONG);
            mToast.show();

            startActivity(i);
        }
    }
    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(Map.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT);
        mToast.show();

        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(Map.this);
        startActivity(i);
    }
}