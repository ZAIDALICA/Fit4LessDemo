package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class CreateReservation extends AppCompatActivity {
    /*
    private static final String TAG = "CreateReservation";
    private TextView onDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);




        /*
        onDisplayDate = (TextView) findViewById(R.id.makeReservation);

        onDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreateReservation.this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));     //This makes the background of the selection transparent
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1 ;
                Log.d(TAG, "onDateSet: mm/dd/yy:" +year+ "/" +month+ "/" +dayOfMonth);

                String date = month + "/" +dayOfMonth+ "/" +year;
                onDisplayDate.setText(date);
            }
        };

        */
    }
    public void selectLocation(View v){
        Intent i = new Intent(this, Map.class);
        startActivity(i);
    }



}