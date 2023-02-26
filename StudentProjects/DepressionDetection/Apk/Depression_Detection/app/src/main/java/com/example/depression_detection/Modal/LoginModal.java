package com.example.depression_detection.Modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModal implements Serializable {
    @SerializedName("response")
    private String response;

    @SerializedName("id")
    private String Id;

    public LoginModal(String response, String id) {
        this.response = response;
        Id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
