package com.example.fit4lessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    protected TextView n;
    protected TextView e;
    protected TextView p;
    protected TextView cp;

    private boolean pass;
    private boolean email;
    private boolean name;
    private boolean blank;

    public final void setRegistration(){
        n = (TextView) findViewById(R.id.Register_NameText);//it's important to ensure we name our buttons and other fields in detail
        e = (TextView) findViewById(R.id.Register_email);
        p = (TextView) findViewById(R.id.Register_Password);
        cp = (TextView) findViewById(R.id.Register_Confirm_Pass);
    }

    private String getConfirm() {
        return cp.getText().toString();
    }
    private String getRegPassword() {
        return p.getText().toString();
    }
    private String getRegEmail() {
        return e.getText().toString();
    }
    private String getName() {
        return n.getText().toString();
    }

    public void checkPass(View view){
        setRegistration();
        //resetting the boolean variables here allows for multiple attempts and prevents one from being locked in true or false states
        pass = false;
        email = false;
        name = true;
        blank = false;
        if(getConfirm().equals(getRegPassword())){
            pass = true;
        }
        if(isValidEmail(getRegEmail())){
            email = true;
        }
        if(getName().equals("")){
            name = false;
        }
        if (getConfirm().equals("") || getRegPassword().equals("")){
            blank = true;
            pass = false;
        }
        completeReg();
    }
    private static boolean isValidEmail(CharSequence target) {// same as in SignIp
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private void completeReg(){// in Registration we take all three pieces of info and will store them to the database, then in SignIn we can pull name from the email Key using a map
        if(email && pass && name){
            Toast.makeText(getApplicationContext(),"Welcome " + getName(),Toast.LENGTH_LONG).show();
            toMain();
        }
        else if(email && !pass && !blank){
            Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_LONG).show();
        }
        else if(email && !pass && blank){
            Toast.makeText(getApplicationContext(),"Passwords cannot be empty",Toast.LENGTH_LONG).show();
        }
        else if(!email){
            Toast.makeText(getApplicationContext(),"Please enter valid Email",Toast.LENGTH_LONG).show();
        }
        else if(!name){
            Toast.makeText(getApplicationContext(),"Please enter a name",Toast.LENGTH_LONG).show();
        }
    }
    private void toMain(){//returns to Main page of app
        Intent i = new Intent(Register.this, MainActivity.class);
        startActivity(i);

    }

}