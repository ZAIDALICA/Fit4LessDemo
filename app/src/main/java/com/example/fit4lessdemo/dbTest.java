package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class dbTest extends AppCompatActivity {


    //refrence to buttons and other controls on the layout
    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        //giving value to the variables the we referenced on top
        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll); //R is the resources folder
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        lv_customerList = findViewById(R.id.lv_customerList);
        sw_activeCustomer = findViewById(R.id.sw_activeCustomer);


        //read below to understand what is this
        dbHelper = new DBHelper(dbTest.this);
        showCustomersOnListView(dbHelper.getEveryone());


        //button listeners
        //we will use self contained button no reference outside the file
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we will make a new customer model instance
                //getting the name and the age from the TextViews that we have
                //we will set the id later
                //made sure to parse the age to integer and name toString
                //Integer.parseInt take a string as an argument so you have to cast the Text to a string


                CustomerModel customerModel;

               try {
                   customerModel = new CustomerModel(-1, et_name.getText().toString(), et_age.getText().toString(), sw_activeCustomer.isChecked(),"","");
                   Toast.makeText(dbTest.this, customerModel.toString(), Toast.LENGTH_SHORT).show(); //put the name of the current class (dbTest) not the mai

               }
               catch (Exception e) {
                   // if the age field is not filled then the app will crash for parsing Int to empty field
                   Toast.makeText(dbTest.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                   //we want to create a customer anyway to let the customer know that something went wrong
                   customerModel = new CustomerModel(-1, "error", "0", false,"","");
               }

               //we will add the customer to the database here after clocking the add button
                DBHelper dBHelper = new DBHelper(dbTest.this); //this is making a reference to the new database

                boolean success = dBHelper.addOne(customerModel);
                //holds the value if the addition is successful or not

                Toast.makeText(dbTest.this, "Success = "+ success, Toast.LENGTH_SHORT).show();

                //to check where the database values are go to
                //View > Tool Windows > Device File Explorer
                //now look for data folder then again > data folder > look at the package name com.example.fit4lessdemo > database
                //now click on the file you need and save it
                //now the files are written in binary, we need to install SQLiteStudio and DB Browser for SQLite
                //lets get DB browser it will be good enough
                // to download https://sqlitebrowser.org/dl/
                //i think sqliteStudio is for MacOs
                //launch the DB Browser for SQLite software and click on open database and import the db file you just saved

                //after having a successful addition we can update the list
                //also look below to understand where the following code comes from
                //since the following code got repeated 3 time {on top and bottom and below then we just refactor the code to a function and call the function instead}
                showCustomersOnListView(dbHelper.getEveryone());


            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(dbTest.this);
                List<CustomerModel> everyone = dbHelper.getEveryone();
                //Toast.makeText(dbTest.this, everyOne.toString(), Toast.LENGTH_SHORT).show();

                //we need to show all the customers instead of just toasting them
                //simple_list_item_1 is a predefined list item in android we can use
                showCustomersOnListView(everyone);

                //i will take the following coding and put it in onCreate method so that it shows all customers as soon as the app open which is what the code under "read below" comment is for
            }
        });


        //we a new way of calling a method is called item click listener //this is to call a method by clicking on an item and it will give you a number of which item was clicked
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel)parent.getItemAtPosition(position); //we have to cast to a customer model
                dbHelper.deleteOne(clickedCustomer);
                showCustomersOnListView(dbHelper.getEveryone());
                Toast.makeText(dbTest.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showCustomersOnListView(List<CustomerModel> everyone) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(dbTest.this, android.R.layout.simple_list_item_1, everyone);
        lv_customerList.setAdapter(customerArrayAdapter);
    }


}