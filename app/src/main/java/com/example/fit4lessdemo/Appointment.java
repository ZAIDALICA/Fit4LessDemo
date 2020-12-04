package com.example.fit4lessdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private Toast mToast = null;

    //Globals
    Context context = this;

    String location="";
    int id = 0;

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
    EditText edTxt_location1;

    //Database handler
    DBBookingsHandler dbBookingsHandler;

    String staff = "";
    String time = "";
    String date = "";

    String dateMap = "";
    String serviceMap = "";
    String staffMap = "";
    String timeMap = "";


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
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT);
            mToast.show();

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

        //getting the date from the date calender activity
        date = dataIntent.getExtras().getString("dateCal");

        //date from the map
        dateMap = dataIntent.getExtras().getString("dateM");
//        serviceMap = dataIntent.getExtras().getString("serviceM");
//        staffMap = dataIntent.getExtras().getString("staffM");
//        timeMap = dataIntent.getExtras().getString("timeM");

        if (date == null || date.equals("")){
            date = dateMap;
//            service = serviceMap;
//            staff = staffMap;
  //          time = timeMap;
        }

        try {
            location = dataIntent.getExtras().getString("location");

            Log.d("Location", location);
        }catch (Exception e){
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(Appointment.this, "No Location is selected", Toast.LENGTH_LONG);
            mToast.show();
        }

//        try {
//            service = dataIntent.getExtras().getString("service");
//            staff = dataIntent.getExtras().getString("staff");
//            dateMap = dataIntent.getExtras().getString("date");
//            time = dataIntent.getExtras().getString("time");
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getMessage();
//        }
        //Log.d("Extrasaaaaaas22222222", dateMap);


        //Create the database handler
        dbBookingsHandler = new DBBookingsHandler(Appointment.this);



        txtDateAppoint = (EditText) findViewById(R.id.txtDateAppointment);
//        editTxt_time = (EditText) findViewById(R.id.editTxt_time);
//        edTxt_staff = (EditText) findViewById(R.id.edTxt_staff);
//
//
//        txtDateAppoint.setText(date);
//        editTxt_time.setText(time);
//        edTxt_staff.setText(staff);


        try {
//            if (!dateMap.equals("")){
//                txtDateAppoint.setText(dateMap);
//            }
//            else{
//                txtDateAppoint.setText(date);
//            }
            txtDateAppoint.setText(date);

        }catch (Exception e){
            txtDateAppoint.setText("");
        }



        //set the username
        userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);
        txtUserNameAppointment = (EditText) findViewById(R.id.txtUserNameAppointment);
        txtUserNameAppointment.setText(userName);

        //same filed for the client
        edTxt_location1 = (EditText) findViewById(R.id.edTxt_location);
        edTxt_location1.setText(location);






        //Add items to the spinner
        fiilSpinners();

        //Set listener View if user taps on date editText

    }


    public void fiilSpinners() {
        spnTime = (Spinner) findViewById(R.id.spnTime);
        String[] times = new String[]{"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
        ArrayAdapter<String> adapterTimes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, times);
        spnTime.setAdapter(adapterTimes);

        spnTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editTxt_time = (EditText) findViewById(R.id.editTxt_time);
                editTxt_time.setText(spnTime.getSelectedItem().toString());

                if (!edTxt_location1.getText().toString().equals("")){
                    String reservationToday = dbBookingsHandler.dbCount(edTxt_location1.getText().toString() ,txtDateAppoint.getText().toString(), editTxt_time.getText().toString(), false,false);
                    Log.d("reservationToday   ", reservationToday);
                    if (mToast != null) mToast.cancel();
                    mToast = Toast.makeText(getApplicationContext(), "You have selected: " + edTxt_location1.getText().toString()+ "| Availability for "+ date +" at "+editTxt_time.getText().toString()+ " is "+ reservationToday+"/3" ,Toast.LENGTH_LONG);
                    mToast.show();
                    mToast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editTxt_time = (EditText) findViewById(R.id.editTxt_time);
                editTxt_time.setText("");  //TODO may need to be removed
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
            public void onNothingSelected(AdapterView<?> parent) { //TODO try it
//                if (staff == null || staff.equals("")){
//                    if (mToast != null) mToast.cancel();
//                    mToast = Toast.makeText(Appointment.this, "Please Select Time", Toast.LENGTH_LONG);
//                    mToast.show();
//                }
                edTxt_staff = (EditText) findViewById(R.id.edTxt_staff);
                edTxt_staff.setText("");  //TODO may need to be removed
            }
        });

        spnService = (Spinner) findViewById(R.id.spnService);
        String[] services = new String[]{"Training day", "Tanning bed", "Massage"};
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
                edTxt_service.setText(""); //TODO
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
    public void btnSubmitAppointmentClicked(View view) { //Todo prevent submitting if one of the fields is missing
        if(location == null || location.equals("")){ //todo location might be null
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(Appointment.this, "Please Select Location", Toast.LENGTH_LONG);
            mToast.show();
        }
        else if (date == null || date.equals("")){
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(Appointment.this, "Please Select Date", Toast.LENGTH_LONG);
            mToast.show();
        }
        else if (!dbBookingsHandler.dbAvailableDay(date,location)) {
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(Appointment.this, "This location is full for "+date, Toast.LENGTH_LONG);
            mToast.show();
        }
        else if (!dbBookingsHandler.dbAvailableTime(spnTime.getSelectedItem().toString(),date,location)) {
            Log.d("CHEKING FOR TIME ", spnTime.getSelectedItem().toString());
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(Appointment.this, "This location is full for this time "+spnTime.getSelectedItem().toString() +" at this day "+date,  Toast.LENGTH_LONG);
            mToast.show();
        }//don't use time here it will be "" rather use the value in the text view of time
        else {
            try {
                //Declare an object of the DB class
                DBBookings appointment = new DBBookings(id,
                        userName,
                        userEmail,
                        spnStaff.getSelectedItem().toString(),
                        spnService.getSelectedItem().toString(),
                        txtDateAppoint.getText().toString(),
                        spnTime.getSelectedItem().toString(),
                        edTxt_location1.getText().toString());

                //Add the user
                dbBookingsHandler.addAppointment(appointment);

                //display confirmation message
                if (mToast != null) mToast.cancel();
                mToast = Toast.makeText(Appointment.this, "Appointment Added", Toast.LENGTH_LONG);
                mToast.show();


                //send the user to the main screen
                Intent bookings = new Intent(this, Bookings.class);
                startActivity(bookings);
            } catch (Exception e) {
                if (mToast != null) mToast.cancel();
                mToast = Toast.makeText(Appointment.this, "Could not add appointment", Toast.LENGTH_LONG);
                mToast.show();

                e.printStackTrace();
                e.getMessage();
            }
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
                        String date3 = String.valueOf(datePicker.getMonth() + 1) + "/" +  String.valueOf(datePicker.getDayOfMonth())  + "/" + String.valueOf(datePicker.getYear());
                        txtDateAppoint.setText(date3);
                        date = date3;

                        String reservationToday = dbBookingsHandler.dbCount(edTxt_location1.getText().toString() ,date,"",true,false);
                        Log.d("reservationToday   ", reservationToday);
                        if (mToast != null) mToast.cancel();
                        mToast = Toast.makeText(getApplicationContext(), "You have selected: " + edTxt_location1.getText().toString()+ "| Availability for "+ date +" is "+ reservationToday+"/7" ,Toast.LENGTH_LONG);
                        mToast.show();
                        mToast.show();
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

    public void btnChangeLocationClicked(View view) {
        Intent i = new Intent(Appointment.this, Map.class);
       // i.putExtra("service", edTxt_service.getText().toString());
        //i.putExtra("staff", edTxt_staff.getText().toString());
        i.putExtra("date",  txtDateAppoint.getText().toString());
        //i.putExtra("time", editTxt_time.getText().toString());
        startActivity(i);
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