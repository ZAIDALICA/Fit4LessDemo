package com.example.fit4lessdemo;

import org.jetbrains.annotations.NotNull;

public class CustomerModel {
    private int id;
    private String name;
    private String age;
    private boolean isActive;
    private String email;
    private String pass;

    //it is better to keep the id in the class not in the database

    //the class constructor
    public CustomerModel(int id, String name, String age, boolean isActive, String email, String pass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.email = email;
        this.pass = pass;
    }

    //overriding constructor in case the class called with empty arguments
    public CustomerModel(){
    }

    // toString is for printing the contents of a class object
    @NotNull
    @Override
    public String toString() {
//        return "CustomerModel{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", isActive=" + isActive +
//                ", email= " + email +
//                ", password= " + pass +
//                '}';
        return
                "Id:" + id +
                "\nName: " + name + '\'' +
                "\nAge: " + age +
                "\nIsActive: " + isActive +
                "\nEmail: " + email +
                "\nPassword:  " + pass ;
    }

    //++++++++++++++++++++++++++++++++ setters and getters for out private data to ensure encapsulation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setEmail(String email){this.email = email;}

    public String getEmail(){return email;}

    public void setPass(String email){this.pass = pass;}

    public String getPass(){return pass;}


    //++++++++++++++++++++++++++++++++ end of setters and getters
}
