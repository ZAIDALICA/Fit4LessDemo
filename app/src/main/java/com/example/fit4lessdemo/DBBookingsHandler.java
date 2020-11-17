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
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMEIN = "timeIn";
    //public static final String COLUMN_TIMEOUT = "timeOut";
    public static final String COLUMN_PRICE = "price";

    //Constructor
    public DBBookingsHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Override methods

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String query = "CREATE TABLE " + TABLE_BOOKINGS + "(" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_USERNAME + " TEXT," +
//                COLUMN_EMAIL + " TEXT," +
//                COLUMN_SERVICE + " TEXT," +
//                COLUMN_DATE + " TEXT," +
//                COLUMN_TIMEIN + " TEXT," +
//                COLUMN_TIMEOUT + " TEXT" +
//                ");";
//        //execute the query
//        try{
//            db.execSQL(query);
//        }catch(SQLiteException e){
//            e.printStackTrace();
//        }


        db.execSQL("CREATE TABLE " + TABLE_BOOKINGS+ " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_EMAIL + " TEXT," +
                        COLUMN_SERVICE + " TEXT," +
                        COLUMN_DATE + " TEXT," +
                        COLUMN_TIMEIN + " TEXT" +
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
        values.put(COLUMN_SERVICE, bookings.get_service());
        values.put(COLUMN_DATE, bookings.get_date());
        values.put(COLUMN_TIMEIN, bookings.get_timeIn());

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
        Log.d("George111",email);
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
                dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                dbString[i] += " email: " + cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE));
                dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                dbString[i] += " TimeIn: " + cursor.getString(cursor.getColumnIndex(COLUMN_TIMEIN));
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


    public String[] getBookingsFromDB(String date){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"";

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

        //TODO I might have to change the loop here to my original way
        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("userName")) != null) {

                //Add the product name to the String and create a new line
                dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                dbString[i] += " email: " + cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE));
                dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                dbString[i] += " TimeIn: " + cursor.getString(cursor.getColumnIndex(COLUMN_TIMEIN));
                //bString += "\n";
                i++;
            }//ends if

            cursor.moveToNext();
        }//ends while loop

        db.close();

        return dbString;
    }


    public boolean deleteOne(CustomerModel customerModel){
        //find customerModer in the database, if found then delete it and return true else return false

        SQLiteDatabase db = this.getWritableDatabase(); //we want to write to the database because we are deleting
        String queryString = "DELETE FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_ID + " = " + customerModel.getId();
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
        }

        return result;
    }


    //if the date contains more than 40 client then the day is not available
    public boolean dbAvailableDay(String date){
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date+ "\"";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.getCount() > 40 ) {
                cursor.close();
                db.close();
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }


}//ends class
