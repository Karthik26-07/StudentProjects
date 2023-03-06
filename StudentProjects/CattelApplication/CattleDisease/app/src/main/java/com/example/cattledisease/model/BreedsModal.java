package com.example.cattledisease.model;

import com.google.gson.annotations.SerializedName;

public class BreedsModal {
    @SerializedName("ID")
    private String ID;
    @SerializedName("Image")
    private String Image;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Description")
    private String Description;
    @SerializedName("Food")
    private String Food;
    @SerializedName("Lifetime")
    private String Lifetime;
    @SerializedName("Cost")
    private String Cost;
    @SerializedName("Origin")
    private String Origin;

    public BreedsModal(String ID, String image, String name, String description, String food, String lifetime, String cost, String origin) {
        this.ID = ID;
        Image = image;
        Name = name;
        Description = description;
        Food = food;
        Lifetime = lifetime;
        Cost = cost;
        Origin = origin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getLifetime() {
        return Lifetime;
    }

    public void setLifetime(String lifetime) {
        Lifetime = lifetime;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }
}
