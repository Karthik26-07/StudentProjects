package com.example.cattledisease.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegistraionModal implements Serializable {
    @SerializedName("message")
    private String Message;

    @SerializedName("username")
    private String Username;

    @SerializedName("password")
    private String Password;

    @SerializedName("phone")
    private  String Phone;

    public RegistraionModal(String message, String username, String password, String phone) {
        Message = message;
        Username = username;
        Password = password;
        Phone = phone;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}

