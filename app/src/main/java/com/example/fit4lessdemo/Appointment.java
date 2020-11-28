package com.example.fit4lessdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v4.app.FragmentActivity;
//Todo Test
public class Appointment extends AppCompatActivity{

    //Globals
    Context context = this;

    int id = 0;
    String date = "";
    String userName = "";
    String userEmail = "";
    String service = "";
    String staffName = "";
    String timeIn = "";
    String timeOut = "";
    int price = 10;

    EditText txtDateAppoint;
    EditText txtUserNameAppointment;
    Spinner spnTime;
    Spinner spnService;
    Spinner spnStaff;

    EditText editTxt_time;
    EditText edTxt_service;
    EditText edTxt_staff;
    EditText edTxt_client;

    //Database handler
    DBBookingsHandler dbBookingsHandler;



    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";


    DBHelper dbCustomer = new DBHelper(Appointment.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);




        //******************** check if the user is logged on **************
        if (SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",Appointment.this).length() == 0) {
            //send to main activity to log in
            Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            //userName = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_NAME",Appointment.this);

            userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",Appointment.this);
            userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);
        }


        //get the date chosen from the intent
        Intent dataIntent = getIntent();
        //get the extras variable put to it
        date = dataIntent.getExtras().getString("date");




        //Create the database handler
        dbBookingsHandler = new DBBookingsHandler(Appointment.this);

        //set the date
        txtDateAppoint = (EditText) findViewById(R.id.txtDateAppointment);
        txtDateAppoint.setText(date);


        //set the username
        userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);
        txtUserNameAppointment = (EditText) findViewById(R.id.txtUserNameAppointment);
        txtUserNameAppointment.setText(userName);

        //same filed for the client
        edTxt_client = (EditText) findViewById(R.id.edTxt_client);
        edTxt_client.setText(userName);

        //Add items to the spinner
        fiilSpinners();

        //Set listener View if user taps on date editText

    }


    public void fiilSpinners() {
        spnTime = (Spinner) findViewById(R.id.spnTime);
        String[] times = new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00","GoToAppoinmentToAdd"};
        ArrayAdapter<String> adapterTimes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
        spnTime.setAdapter(adapterTimes);

        spnTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editTxt_time = (EditText) findViewById(R.id.editTxt_time);
                editTxt_time.setText(spnTime.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editTxt_time = (EditText) findViewById(R.id.editTxt_time);
                editTxt_time.setText("");
            }
        });

        spnStaff = (Spinner) findViewById(R.id.spnStaff);
        String[] staff = new String[]{"George", "Matt", "Mujtaba", "Julia", "Suhail"};
        ArrayAdapter<String> adapterStaff = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, staff);
        spnStaff.setAdapter(adapterStaff);


        spnStaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edTxt_staff = (EditText) findViewById(R.id.edTxt_staff);
                edTxt_staff.setText(spnStaff.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edTxt_staff = (EditText) findViewById(R.id.edTxt_staff);
                edTxt_staff.setText("");
            }
        });

        spnService = (Spinner) findViewById(R.id.spnService);
        String[] services = new String[]{"Training day", "Tanning bed", "Massage", "GoToAppoinmentToAdd"};
        ArrayAdapter<String> adapterServices = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, services);
        spnService.setAdapter(adapterServices);

        spnService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edTxt_service = (EditText) findViewById(R.id.edTxt_service);
                edTxt_service.setText(spnService.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edTxt_service = (EditText) findViewById(R.id.edTxt_service);
                edTxt_service.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointments, menu);
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


    //******************* method submit appointment *****************
    public void btnSubmitAppointmentClicked(View view) {
        try {
            //Declare an object of the DB class
            DBBookings appointment = new DBBookings(id,
                    userName,
                    userEmail,
                    spnService.getSelectedItem().toString(),
                    spnStaff.getSelectedItem().toString(),
                    txtDateAppoint.getText().toString(),
                    spnTime.getSelectedItem().toString());

            //Add the user
            dbBookingsHandler.addAppointment(appointment);

            //display confirmation message
            Toast.makeText(Appointment.this, "Appointment Added", Toast.LENGTH_LONG).show();

            //send the user to the main screen
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        } catch (Exception e) {
            Toast.makeText(Appointment.this, "Could not add appointment", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            e.getMessage();
        }

    }//ends submit appointment


    public void btnCancelAppointmentsClicked(View view) {

        //take the user to booking activity
        Intent bookingsIntent = new Intent(Appointment.this, Bookings.class);
        startActivity(bookingsIntent);

    }


    //****************************** change date button clicked
    public void btnChangeDateClicked(View view){

        //ge the layout login.xml to use and display
        LayoutInflater li = LayoutInflater.from(context);
        View logInView = li.inflate(R.layout.time_picker, null);

        //create the Alert Dialog box to call the date picker
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(logInView);

        final DatePicker datePicker = (DatePicker) logInView.findViewById(R.id.datePickerTwo);

        alertDialog
                .setCancelable(true)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String date = String.valueOf(datePicker.getMonth() + 1) + "/" +  String.valueOf(datePicker.getDayOfMonth())  + "/" + String.valueOf(datePicker.getYear());
                        txtDateAppoint.setText(date);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TO-DO
                    }
                });

        AlertDialog alertDialogShow = alertDialog.create();
        alertDialogShow.show();
    }



//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}