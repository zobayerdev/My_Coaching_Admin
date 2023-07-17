package com.trodev.mycoaching.models;

import android.net.Uri;

public class Student {
    private String uid;
    private String name;
    private String surname;

    private String email;
    private String phoneNumber;
    private String password;
    private String photoUri;
    private String Status;
    public Student(){}

    public Student(String uid,String name, String surname, String email, String phoneNumber, String password,String photoUri,String Status) {
        this.uid=uid;
        this.name = name;
        this.surname = surname;

        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.photoUri =photoUri ;
        this.Status=Status;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
