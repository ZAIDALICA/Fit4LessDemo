package com.example.fit4lessdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Notification extends AppCompatActivity {
    private Toast mToast = null;
    String userName = "";
    String userEmail = "";
    DBHelper dbCustomer = new DBHelper(Notification.this);



    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BottomNavigationView bNav;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);




        // Finds you button from the xml layout file
        Button createNotificationButton = findViewById(R.id.button_create_notification);

        // Waits for you to click the button
        createNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts the function below
                addNotification();
            }
        });




        bNav = findViewById(R.id.nav_view);
        bNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_notifications:
                        //startActivity(new Intent(Notification.this, Notification.class));
                        return true;
                    case R.id.navigation_bookings:
                        startActivity(new Intent(Notification.this, DeveloperPage.class));
                        return true;
                    case R.id.navigation_home:
                        //Log.d("Cliiiiicked", "home");
                        startActivity(new Intent(Notification.this, NewsPage.class));
                        return true;
                    case R.id.navigation_logout:
                        logMeOutVoid();
                }
                return false;
            }
        });



        if (SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL", this).length() == 0) {
            //send to main activity to log in
            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(getApplicationContext(), "Please Log In", Toast.LENGTH_SHORT);
            mToast.show();

            Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(mainIntent);
        } else {
            //get the user name
            userEmail = SaveUserLoginPreferences.getUserLoginSharedPreferences("PREF_EMAIL",Notification.this);  //you cant have this before the on create method of the class
            userName = dbCustomer.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, userEmail);

        }

    }




    private void addNotification() {
        String CHANNEL_ID = "my_channel_01";
        //From the developer documentation:

        //When you target Android 8.0 (API level 26), you must implement one or more notification channels to display notifications to your users.

        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }


        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Hi "+userName+"!")
                .setContentText("Your session today is in one hour")
                .setSound(uri);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.mipmap.f4l);

            builder.setColor(getResources().getColor(R.color.purple_200));
        } else {
            builder.setSmallIcon(R.mipmap.f4l);
        }

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());


//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("George Android Studio Test")
//                .setContentText("A video has just arrived!");

        // Creates the intent needed to show the notification
//        Intent notificationIntent = new Intent(this, Notification.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }




    public void logMeOutVoid() {
        //go to the login page again
        Intent i = new Intent(Notification.this, SignIn.class);
        String byUsername = MainActivity.getSavedName();
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), "Bye "+byUsername, Toast.LENGTH_SHORT);
        mToast.show();
        //clearing the user preferences
        SaveUserLoginPreferences.clearUserLoginSharedPreferences(Notification.this);
        startActivity(i);
    }
}