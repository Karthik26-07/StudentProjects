package com.example.cattledisease.model;

public class SymptomListModel {
    private String Name;
    private Boolean isSelected;

    public SymptomListModel(String name, Boolean isSelected) {
        Name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
