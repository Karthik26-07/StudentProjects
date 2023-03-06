package com.example.cattledisease.model;

import com.google.gson.annotations.SerializedName;

public class AccountsModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;

    public AccountsModel(String name, String username, String password, String email, String phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public AccountsModel(String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
