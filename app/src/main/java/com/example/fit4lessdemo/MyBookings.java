package com.example.fit4lessdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MyBookings extends AppCompatActivity {
    private Toast mToast = null;


    //DB handler
    DBBookingsHandler dbBookingsHandler;
    DBBookings oneBooking;
    String userName = "George";
    String userEmail="";

    ArrayAdapter customerArrayAdapter;
    ListView lViewMyBookings;


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bookingsDB.db";
    public static final String TABLE_BOOKINGS = "bookings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_STAFF = "staff";
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMEIN = "timeIn";
    public static final String COLUMN_LOCATION = "location";

    BottomNavigationView bNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        lViewMyBookings = findViewById(R.id.lViewMyBookings);
        dbBookingsHandler = new DBBookingsHandler(MyBookings.this);
        userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",MyBookings.this);

        showCustomersOnListView(dbBookingsHandler.getEveryone(COLUMN_EMAIL ,userEmail,false));

        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(MyBookings.this, NewsPage.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                }
                return false;
            }
        });


   // try {
//        dbBookingsHandler = new DBBookingsHandler(MyBookings.this);
//        userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",MyBookings.this);
        //DBHelper dbCustomer = new DBHelper(MyBookings.this);




        //check the number of reservations already made, so make an if statement

        //userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);

        //Log.d("George",userEmail);
       // List <DBBookings> allBookings = dbBookingsHandler.getEveryone(COLUMN_EMAIL ,userEmail);

        //create an array of strings of the size of how many rows have returned
//        String myCurrentBookings[] = new String[myBookings.length];
//
//        //check for null values
//        for(int i =0; i<myBookings.length;i++){
//            if(myBookings[i] != null){
//                myCurrentBookings[i] = myBookings[i];
//            }
//        }

        //create the adapter to convert Array of strings into list items
      //  ListAdapter wilsAdapter = new BookingsMyListViewAdapter(this, myBookings);


        //declare your list view
//        ListView wilsListView = (ListView) findViewById(R.id.lViewMyBookings);
//        //Convert the array of strings and add it to the list view
//        wilsListView.setAdapter(wilsAdapter);
//
//        //create a listener for each item on the list view to respond to touch
//        wilsListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener(){
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        oneBooking = (DBBookings)parent.getItemAtPosition(position); //we have to cast to a customer model
//                        Toast.makeText(MyBookings.this, oneBooking.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }
//        );//ends setOnItemClickListener

//    }catch (Exception e){
//        e.printStackTrace();
//        e.getMessage();
//    }


        lViewMyBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneBooking = (DBBookings)parent.getItemAtPosition(position); //we have to cast to a customer model
                if (mToast != null) mToast.cancel();
                mToast =  Toast.makeText(MyBookings.this, "Selected: "+oneBooking.toString(), Toast.LENGTH_LONG);
                mToast.show();

                //dbBookingsHandler.deleteOne( oneBooking);
                showCustomersOnListView(dbBookingsHandler.getEveryone(COLUMN_EMAIL ,userEmail,false));
                //Toast.makeText(MyBookings.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_bookings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  //TODO Need to remove this setting or fill it with something
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showCustomersOnListView(List<DBBookings> everyone) {
        customerArrayAdapter = new ArrayAdapter<DBBookings>(MyBookings.this, android.R.layout.simple_list_item_1, everyone);
        lViewMyBookings.setAdapter(customerArrayAdapter);
    }

    public void cancelBooking(View v){
        try {
            dbBookingsHandler.deleteOne(oneBooking);
            showCustomersOnListView(dbBookingsHandler.getEveryone(COLUMN_EMAIL ,userEmail,false));
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(MyBookings.this, "Deleted", Toast.LENGTH_SHORT);
            mToast.show();

        }catch (Exception e){
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(MyBookings.this, "Select a Booking", Toast.LENGTH_LONG);
            mToast.show();

        }

    }
    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(MyBookings.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT);
        mToast.show();

        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(MyBookings.this);
        startActivity(i);
    }

}