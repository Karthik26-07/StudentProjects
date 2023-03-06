package com.example.cattledisease.model;

import com.google.gson.annotations.SerializedName;

public class DoctorsModel {

    @SerializedName("name")
    private String dname;
    @SerializedName("email")
    private String demail;
    @SerializedName("contact")
    private String dphone;

    public DoctorsModel(String dname, String demail, String dphone) {
        this.dname = dname;
        this.demail = demail;
        this.dphone = dphone;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }
}
