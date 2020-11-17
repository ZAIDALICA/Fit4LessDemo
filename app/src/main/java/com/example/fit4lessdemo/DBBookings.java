package com.example.fit4lessdemo;

import org.jetbrains.annotations.NotNull;

public class DBBookings {

    private int _id;
    private String _userName;


    private String _email;
    private String _service;
    private String _date;
    private String _timeIn;
    private String _timeOut;
//    private int _price;

    //Constructors
    public DBBookings() {
    }
    public DBBookings(String _userName, String _email, String _service, String _date, String _timeIn) {
        this._userName = _userName;
        this._email = _email;
        this._service = _service;
        this._date = _date;
        this._timeIn = _timeIn;
    }


    @NotNull
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + _id +
                ", name='" + _userName + '\'' +
                ", email=" + _email +
                ", service=" + _service +
                ", date= " + _date +
                ", timeIn= " + _timeIn +
                ", timeOut= " + _timeOut +
                '}';
    }




    //getters and Setters
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_service() {
        return _service;
    }

    public void set_service(String _service) {
        this._service = _service;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_timeIn() {
        return _timeIn;
    }

    public void set_timeIn(String _timeIn) {
        this._timeIn = _timeIn;
    }

    public String get_timeOut() {
        return _timeOut;
    }

    public void set_timeOut(String _timeOut) {
        this._timeOut = _timeOut;
    }

//    public int get_price() {
//        return _price;
//    }
//
//    public void set_price(int _price) {
//        this._price = _price;
//    }
}
