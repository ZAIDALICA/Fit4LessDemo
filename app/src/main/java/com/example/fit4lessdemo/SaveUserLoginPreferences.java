package com.example.fit4lessdemo;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SaveUserLoginPreferences {

    static final String PREF_NAEM = "";
    static final String PREF_EMAIL = "";
    static final String PREF_PASS = "";

    SharedPreferences sharedpreferences;

    static SharedPreferences getUserSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserLoginSharedPreferences(String KEY, String value, Context ctx){
        SharedPreferences.Editor editor = getUserSharedPreferences(ctx).edit();
        //add the username to the shared pref file
        editor.putString(KEY, value);
        editor.commit();
    }

    //get the username logged in
    public static String getUserLoginSharedPreferences(String KEY,Context ctx){
        return getUserSharedPreferences(ctx).getString(KEY, "");
    }

    //Log the user out
    public static void logOut(Context ctx){
        SharedPreferences.Editor editor = getUserSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }


}