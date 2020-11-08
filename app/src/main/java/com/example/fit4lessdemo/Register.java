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
    protected TextView a;

    DBHelper helper = new DBHelper(Register.this);

    private boolean pass;
    private boolean email;
    private boolean name;
    private boolean blank;
    private boolean age;
    private boolean unique;

    public final void setRegistration(){
        n = (TextView) findViewById(R.id.Register_NameText);//it's important to ensure we name our buttons and other fields in detail
        e = (TextView) findViewById(R.id.Register_email);
        p = (TextView) findViewById(R.id.Register_Password);
        cp = (TextView) findViewById(R.id.Register_Confirm_Pass);
        a = (TextView) findViewById(R.id.Register_Age);
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
    private String getAge(){return a.getText().toString();}

    public void checkPass(View view){
        setRegistration();
        //resetting the boolean variables here allows for multiple attempts and prevents one from being locked in true or false states
        pass = false;
        email = false;
        unique = false;
        name = true;
        blank = false;
        age = true;
        if(getConfirm().equals(getRegPassword())){
            pass = true;
        }
        if (helper.uniqueEmail(getRegEmail())){
            unique = true;
        }
        if(isValidEmail(getRegEmail())){
            //George: the isValid must be developed to prevent existing emails in the database
            email = true;
        }
        if(getName().equals("")){
            name = false;
        }
        if (getConfirm().equals("") || getRegPassword().equals("")){
            blank = true;
            pass = false;
        }
        if (getAge().equals("")){
            age = false;
        }
        completeReg();
    }
    private static boolean isValidEmail(CharSequence target) {// same as in SignIp
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private void completeReg(){// in Registration we take all three pieces of info and will store them to the database, then in SignIn we can pull name from the email Key using a map
        if(email && pass && name && age && unique){
            Toast.makeText(getApplicationContext(),"Welcome " + getName(),Toast.LENGTH_LONG).show();
            CustomerModel cM = new CustomerModel(1, getName(), getAge(),true, getRegEmail(), getRegPassword());
            helper.addOne(cM);
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
        else if (!age){
            Toast.makeText(getApplicationContext(),"Please enter your age",Toast.LENGTH_LONG).show();
        }
        else if (!unique)
            Toast.makeText(getApplicationContext(),"Email is already Registered",Toast.LENGTH_LONG).show();
    }
    private void toMain(){//returns to Main page of app
        Intent i = new Intent(Register.this, MainActivity.class);
        startActivity(i);

    }

}