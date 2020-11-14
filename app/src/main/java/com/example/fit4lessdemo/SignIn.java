package com.example.fit4lessdemo;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_LOGIN_PASSWORD = "LOGIN_PASSWORD";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
    DBHelper helper = new DBHelper(SignIn.this);
    protected TextView e;
    protected TextView p;
    protected void setLoginTry(){ // attempt at making information secure
        e = (TextView) findViewById(R.id.signIn_emial); //in order to pull the typed info from user we must cast TextView
        p = (TextView) findViewById(R.id.signIn_password);
    }
    //The Getter is how we convert the TextView to String
    private String getPass(){
        return p.getText().toString();
    }
    private String getEmail(){
        return e.getText().toString().toLowerCase();  //george: done ^_^
    }//we will need to make this return in all lowercase, ensuring the user can enter it in either or. but we will also make the Database save in all lower case as well

    private void testLogin() { //Here will run a method for the database, probably making it a separate class all together, opposed to my custom Strings for testing purposes
        if (isValidEmail(getEmail())) {
            //String testPass = helper.passwordCheck(getEmail());
            if (helper.dbCheck(COLUMN_CUSTOMER_EMAIL,getEmail())){ //this function now takes one field and the entered data
                String bdPass = helper.dbGet(COLUMN_LOGIN_PASSWORD, COLUMN_CUSTOMER_EMAIL ,getEmail());
                if (bdPass.equals(getPass())){
                    Toast.makeText(getApplicationContext(),"Welcome " + helper.dbGet(COLUMN_CUSTOMER_NAME, COLUMN_CUSTOMER_EMAIL, getEmail()) + "!",Toast.LENGTH_LONG).show();
                    SaveUserLoginPreferences.setUserLoginSharedPreferences("PREF_EMAIL", getEmail(), this);  //saving the data so the app will use it to log in automatically later
                    SaveUserLoginPreferences.setUserLoginSharedPreferences("PREF_PASS", bdPass, this);  //saving the data so the app will use it to log in automatically later
                    //TODO we also need to get the username from the database to save it as a preference so that we can use the username when greeting or for the name that will appear on top of the home screen
                    //We also have to sent with the intent the user information to the main so that the main can act knowing which user logged in and act accordingly
                    backToMain();
                }else {
                    Toast.makeText(getApplicationContext(),"Password is Incorrect",Toast.LENGTH_LONG).show();
                }

            }
            else {
                Toast.makeText(getApplicationContext(), "Email is incorrect",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();
        }
    }
    private static boolean isValidEmail(CharSequence target) {//Simple line of code to ensure emails are correctly typed
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void backToMain(){ //Returns to main page upon successful login
        Intent i = new Intent(SignIn.this, NewsPage.class);
        startActivity(i);
    }

    public void onLoginAttempt(View v){//runs when 'Login' button is clicked
            //George: We can have a counter here to eliminate the user from trying many times
            // and send him to support or block him for some amount of time
            setLoginTry();
            testLogin();
    }

    public void sendRegister(View view){//runs when register button is pushed
        Intent i = new Intent(SignIn.this, Register.class);
        startActivity(i);
    }

}
