package com.example.fit4lessdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBBookingsHandler extends SQLiteOpenHelper {

    //String date = "";

    //dclare the database elements
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
    //public static final String COLUMN_TIMEOUT = "timeOut";
    //public static final String COLUMN_PRICE = "price";

    //Constructor
    public DBBookingsHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Override methods

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_BOOKINGS+ " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_EMAIL + " TEXT," +
                        COLUMN_STAFF + " TEXT," +
                        COLUMN_SERVICE + " TEXT," +
                        COLUMN_DATE + " TEXT," +
                        COLUMN_TIMEIN + " TEXT," +
                        COLUMN_LOCATION + " TEXT" +
                 ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop the current table and upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);

        //call the create database
        onCreate(db);
    }

    //*************************** Add to DB method ********************
    public void addAppointment(DBBookings bookings){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, bookings.get_userName());
        values.put(COLUMN_EMAIL, bookings.get_email());
        values.put(COLUMN_STAFF, bookings.get_staff());
        values.put(COLUMN_SERVICE, bookings.get_service());
        values.put(COLUMN_DATE, bookings.get_date());
        values.put(COLUMN_TIMEIN, bookings.get_timeIn());
        values.put(COLUMN_LOCATION, bookings.get_location());

        //add the rest here

        //declare the database
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_BOOKINGS, null, values);
            db.close();
        }catch(SQLiteException e){
            e.printStackTrace();
        }

    }//ends add appointment


    //****************Print all the data from the database *************************
    public String[] getMyBookingsFromDB(String email){
        String dbString1[]= new String[2];
        //Log.d("George111",email);
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_EMAIL + " =\"" + email + "\"" + " ORDER BY " + COLUMN_DATE + " DESC";

        //cursor point to a location in your results Eg, first or last result
        try {
        Cursor cursor = db.rawQuery(query, null);
        //Move to first row in results
        //create integer to loop through
        int i = 0;

        //create an empty string to hold the values
        String dbString[] = new String[cursor.getCount()];

        //loop through the results starting from first row

        //TODO I might have to change the loop here to my original way
        if (cursor.moveToFirst()) {
            do {

                //Add the product name to the String and create a new line
                dbString[i] = "ID: " + cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                dbString[i] += " Email: " + cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                dbString[i] += " Staff: " + cursor.getString(cursor.getColumnIndex(COLUMN_STAFF));
                dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE));
                dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                dbString[i] += " TimeIn: " + cursor.getString(cursor.getColumnIndex(COLUMN_TIMEIN));
                dbString[i] += " Location: " + cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                //bString += "\n";
                i++;
            }while (cursor.moveToNext());
        }//ends while loop

            cursor.close();
            db.close();
            return dbString;
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }



        return dbString1;
    }


    public String[] getBookingsFromDB(String date, String email, boolean dbGetAll){
        String query;
        SQLiteDatabase db = getWritableDatabase();
        if (dbGetAll){
            query = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"" + " ORDER BY " + COLUMN_DATE + " DESC";
        }else {
            query= "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"" + " AND " + COLUMN_EMAIL+ " =\"" + email+ "\"" + " ORDER BY " + COLUMN_DATE + " DESC";
        }


        //cursor point to a location in your results Eg, first or last result
        //try {
        Cursor cursor = db.rawQuery(query, null);
        //Move to first row in results
        cursor.moveToFirst();
        //create integer to loop through
        int i = 0;

        //create an empty string to hold the values
        String dbString[] = new String[cursor.getCount()];

        //loop through the results starting from first row

        if (cursor.moveToFirst()) {
            do {

                //Add the product name to the String and create a new line
                dbString[i] = "ID: " + cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                dbString[i] += " Email: " + cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE));
                dbString[i] += " Staff: " + cursor.getString(cursor.getColumnIndex(COLUMN_STAFF));
                dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                dbString[i] += " TimeIn: " + cursor.getString(cursor.getColumnIndex(COLUMN_TIMEIN));
                dbString[i] += " Location: " + cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                //bString += "\n";
                i++;
            }while(cursor.moveToNext());
        }//ends while loop

        db.close();

        return dbString;
    }


    public boolean deleteOne(DBBookings dbBookings){
        //find customerModer in the database, if found then delete it and return true else return false

        SQLiteDatabase db = this.getWritableDatabase(); //we want to write to the database because we are deleting
        String queryString = "DELETE FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_ID + " = " + dbBookings.get_id();  //It's better to delete by Id not by email because one email can have many bookings
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }

    }



    public String dbGet(String selectField, String whereField, String item){
        String result = "";
        String queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + whereField + " =\"" + item+ "\"";
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            cursor.moveToFirst();
            result = cursor.getString(cursor.getColumnIndex(selectField));
            //Log.d("result", result);
            cursor.close();
            db.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }

        return result;
    }


    //if the date contains more than 10 client then the day is not available
    public boolean dbAvailableDay(String date ,String location){
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"" + " AND " + COLUMN_LOCATION + " =\"" + location+ "\"" ;
        Log.d("avDate", queryString);
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.getCount() > 6 ) { //if the db has more than 10 bookings for this day then you can not book a reservation
                cursor.close();
                db.close();
                return false;
            }
        } catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return false;
        }
        return true;

    }    //if the date contains more than 5 client then the day is not available


    public boolean dbAvailableTime(String time, String date, String location){ //searching for the number of bookings in one hour of one day in a certain location
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date+ "\"" + " AND " + COLUMN_TIMEIN + " =\"" + time + "\"" + " AND " + COLUMN_LOCATION + " =\"" + location + "\""  ;
        Log.d("avTIme", queryString);
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.getCount() > 2) {
                cursor.close();
                db.close();
                cursor.close();
                return false;
            }
        } catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public String dbCount(String location,String date, String time, boolean boolDate, boolean dbGetAllBookings){
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString;
        if (dbGetAllBookings) {
            queryString = "SELECT * FROM " + TABLE_BOOKINGS;
        }else if (boolDate) {
            queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"" + " AND " + COLUMN_LOCATION + " =\"" + location+ "\"" ;
        }else{
            queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"" + " AND " + COLUMN_LOCATION + " =\"" + location+ "\"" + " AND " + COLUMN_TIMEIN + " =\"" + time+ "\"" ;
        }
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            Log.d("CCCCCCCOUBT", String.valueOf(cursor.getCount()) + " >>>>>>>>>>>>> "+queryString);
            return String.valueOf(cursor.getCount());
        } catch (Exception e){
            return "0";
        }
    }


    public List<DBBookings> getEveryone(String Field,String Item, boolean dbGetAll) {
        List<DBBookings> returnList = new ArrayList<>();
        //get data from the database
        String queryString;
        if (dbGetAll) {
            queryString = "SELECT * FROM " + TABLE_BOOKINGS;
        } else {
            queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + Field + " =\"" + Item + "\""; //this is a standard sql query string
        }

        SQLiteDatabase db = this.getReadableDatabase(); //we need to read from the database
        //getReadable would work here as well but it will lock the database so other processes may not access it

        //we can either choose db.execSql or db.rawQuery we will choose raw here
        //notice the raw query returns a cursor object
        //cursor is the result set //it is a complex arrays of data
        Cursor cursor = db.rawQuery(queryString, null);


        //we will check if the cursor is not empty so we go the the first item in it

        if (cursor.moveToFirst()) {
            do {
                // loop through the cursor (result set) and create new customer objects. put them into the return list
                //we will get the data from the cursor
                //we know that the first column is the id so we will use the index 0 of the cursor
                int customerId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));  //column can be also called like that
                String Client = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String Email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String Staff = cursor.getString(cursor.getColumnIndex(COLUMN_STAFF));
                String Service = cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE));
                String Date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                String TimeIn = cursor.getString(cursor.getColumnIndex(COLUMN_TIMEIN));
                String Location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                //the problem now is that in sqlite there is no such thing as boolean the value is either 0 or one
                //so we take that int and convert to boolean

                //now making the customer from the data that we got from the cursor
                DBBookings dbBookings = new DBBookings(customerId, Client, Email, Staff, Service, Date, TimeIn, Location);

                //now adding the customer to the list
                returnList.add(dbBookings);
            } while (cursor.moveToNext());
        }
        cursor.close(); //we have to close the cursor just like we close a file after reading or writing
        db.close(); //also the db

        return returnList;
    }



}//ends class
