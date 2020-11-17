package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MyBookings extends AppCompatActivity {


    //DB handler
    DBBookingsHandler dbBookingsHandler;
    String userName = "George";



    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        String userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",MyBookings.this);
        DBHelper dbCustomer = new DBHelper(MyBookings.this);

        //check the number of reservations already made, so make an if statement

        //userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);
        Log.d("George",userEmail);
        String myBookings[];

        myBookings = dbBookingsHandler.getMyBookingsFromDB(userEmail);

        //create an array of strings of the size of how many rows have returned
        String myCurrentBookings[] = new String[myBookings.length];

        //check for null values
        for(int i =0; i<myBookings.length;i++){
            if(myBookings[i] != null){
                myCurrentBookings[i] = myBookings[i];
            }
        }

        //create the adapter to convert Array of strings into list items
        ListAdapter wilsAdapter = new BookingsMyListViewAdapter(this, myBookings);


        //declare your list view
        ListView wilsListView = (ListView) findViewById(R.id.lViewMyBookings);
        //Convert the array of strings and add it to the list view
        wilsListView.setAdapter(wilsAdapter);

        //create a listener for each item on the list view to respond to touch
        wilsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String bookingDetails = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MyBookings.this, bookingDetails, Toast.LENGTH_LONG).show();
                    }
                }
        );//ends setOnItemClickListener


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_bookings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

}