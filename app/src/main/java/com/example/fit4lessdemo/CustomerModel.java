package com.example.fit4lessdemo;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    //it is better to keep the id in the class not in the database

    //the class constructor
    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    //overriding constructor in case the class called with empty arguments
    public CustomerModel(){
    }

    // toString is for printing the contents of a clas object
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    //++++++++++++++++++++++++++++++++ end of setters and getters
}
