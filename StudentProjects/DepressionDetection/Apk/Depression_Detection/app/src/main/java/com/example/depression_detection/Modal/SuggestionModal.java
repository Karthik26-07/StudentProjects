package com.example.depression_detection.Modal;

import com.google.gson.annotations.SerializedName;

public class SuggestionModal {
    @SerializedName("Suggestion")
    private String Suggestion;
    @SerializedName("Title")
    private String Title;

    public SuggestionModal(String suggestion, String title) {
        Suggestion = suggestion;
        Title = title;
    }

    public String getSuggestion() {
        return Suggestion;
    }

    public void setSuggestion(String suggestion) {
        Suggestion = suggestion;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
