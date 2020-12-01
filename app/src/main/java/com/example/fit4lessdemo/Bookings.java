package com.example.fit4lessdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;




public class Bookings extends AppCompatActivity {
    private Toast mToast = null;
    //globals
    String userName = "";
    //SimpleDateFormat sdf = new SimpleDateFormat("cc/MM//yy");
    //String date = sdf.format(new Date());
    String date = "";
    TextView lblUserNameBookings;
    TextView disablePastDate, disableFutureDate;
    CalendarView cViewBookings;

    //DB handler
    DBBookingsHandler dbBookingsHandler;


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";


    String userEmail;
    DBHelper dbCustomer = new DBHelper(Bookings.this);
    BottomNavigationView bNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        //disablePastDate = findViewById(R.id.disablePastDate);


        lblUserNameBookings = (TextView) findViewById(R.id.lblUserNameBookings);


        //Database to read day's select bookings
        dbBookingsHandler = new DBBookingsHandler(this);
        //String[] todayBookings = dbBookingsHandler.getBookingsFromDB("01/02/2015");



        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        //Log.d("Cliiiiicked", "home");
                        startActivity(new Intent(Bookings.this, NewsPage.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                }
                return false;
            }
        });


        //******************** check if the user is logged on **************
        if (SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL", this).length() == 0) {
            //send to main activity to log in
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT);
            mToast.show();

            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",Bookings.this);  //you cant have this before the on create method of the class
            userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);

            //Assign the label to the the username
            lblUserNameBookings.setText(userName);
        }

        //Get today's date
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date = String.valueOf(month + 1) + "/" + String.valueOf(day)  + "/" + String.valueOf(year);

        //Call the method to poppulate the List view with bookings on that day
        fillListView();

        //style the CalendarView
        cViewBookings = (CalendarView) findViewById(R.id.cViewBookings);
        cViewBookings.setShowWeekNumber(false);
        cViewBookings.setSelectedDateVerticalBar(R.color.common_google_signin_btn_text_light_focused);
        cViewBookings.setSelectedWeekBackgroundColor(getResources().getColor(R.color.purple_700));
        cViewBookings.isClickable();
        cViewBookings.isLongClickable();

        //******************** on date changed calendar ****************
        cViewBookings.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                date = String.valueOf(month + 1)  + "/" + String.valueOf(day) + "/" + String.valueOf(year);
                if (mToast != null) mToast.cancel();
                mToast = Toast.makeText(getApplicationContext(), (month + 1)  + "/" +  day + "/" + year, Toast.LENGTH_SHORT);
                mToast.show();
                //Call that method to poppulate the List view with bookings on htat day
                fillListView();

            }//ends on select date changed
        });//ends on date change listener


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //manage action bar icons
        switch (id) {
            case R.id.action_settings: { //TODO the notification system
                return true;
            }
            case R.id.action_add_appointment: {
                //call the appointment activity
                Intent appointmentIntent = new Intent(this, Appointment.class);
                appointmentIntent.putExtra("dateCal", date); //sending the date to the oppointment activity
                startActivity(appointmentIntent);

                return true;
            }
            case R.id.action_refresh_bookings: {
                //refresh the bookings
                fillListView();
                if (mToast != null) mToast.cancel();
                mToast = Toast.makeText(this, "Bookings updated", Toast.LENGTH_LONG);
                mToast.show();;
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }//ends switch
    }//ends action bar items


    //*********************** Fill list view **********************************
    public void fillListView() {

        //String to hold the number of values returned from the database
        String todayBookings[];

        //Query the database for the day selected
        todayBookings = dbBookingsHandler.getBookingsFromDB(date);

       // Log.d("todayBookings", Arrays.toString(todayBookings));
        //create an array of strings of the size of how many row have returned
        String tBookings[] = new String[todayBookings.length];

        //check for null values
        for(int i =0; i<todayBookings.length;i++){
            if(todayBookings[i] != null){
                tBookings[i] = todayBookings[i];
            }
        }

        //List<String> dbString = new ArrayList<String>();

        //populate the List View
        ListAdapter wilsAdapter = new BookingsListViewAdapter(this, tBookings);

        //declare the list View
        ListView lViewBookings = (ListView) findViewById(R.id.lViewBookings);

        //Convert the array of strings and add it to the list view
        lViewBookings.setAdapter(wilsAdapter);

        //create a listener for each item on the list view to respond to touch
        lViewBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String booking = String.valueOf(parent.getItemAtPosition(position));
                if (mToast != null) mToast.cancel();
                mToast = Toast.makeText(Bookings.this, booking, Toast.LENGTH_SHORT);
                mToast.show();

            }
        });//ends on click on list view
    }

    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(Bookings.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT);
        mToast.show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(Bookings.this);
        startActivity(i);
    }

}