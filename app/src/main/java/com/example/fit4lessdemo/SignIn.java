package com.example.fit4lessdemo;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
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
        return e.getText().toString();
    }//we will need to make this return in all lowercase, ensuring the user can enter it in either or. but we will also make the Database save in all lower case as well

    private void testLogin() { //Here will run a method for the database, probably making it a separate class all together, opposed to my custom Strings for testing purposes
        String testE = "Matt@gmail.com"; //Temporary for testing
        String testP = "Hello123";
        boolean emailCheck = false;
        boolean passwordCheck = false;
        if (!isValidEmail(getEmail())){
            Toast.makeText(getApplicationContext(),"Please enter a valid email",Toast.LENGTH_LONG).show();//this creates the pop-up message
        }
        if (testE.equals(getEmail())){
            emailCheck = true;
        }
        if (testP.equals(getPass())){
            passwordCheck = true;
        }
        if (emailCheck && !passwordCheck){
            Toast.makeText(getApplicationContext(),"Password Incorrect", Toast.LENGTH_LONG).show();
        }
        if (!emailCheck){
            Toast.makeText(getApplicationContext(),"Information Incorrect", Toast.LENGTH_LONG).show();
        }
        if (emailCheck && passwordCheck) {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
            backToMain();
        }

    }
    private static boolean isValidEmail(CharSequence target) {//Simple line of code to ensure emails are correctly typed
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void backToMain(){ //Returns to main page upon successful login
        Intent i = new Intent(SignIn.this, MainActivity.class);
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
