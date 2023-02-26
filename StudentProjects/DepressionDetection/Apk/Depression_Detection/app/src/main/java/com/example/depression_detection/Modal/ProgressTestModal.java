package com.example.depression_detection.Modal;

import com.google.gson.annotations.SerializedName;

public class ProgressTestModal {
    @SerializedName("id")
    private String Pr_QuestionId;
    @SerializedName("Pr_question")
    private String Pr_Question;
    @SerializedName("Pr_option1")
    private String Pr_option1;
    @SerializedName("Pr_option2")
    private String Pr_option2;
    @SerializedName("Pr_option3")
    private String Pr_option3;
    @SerializedName("Pr_option4")
    private String Pr_option4;
    @SerializedName("Pr_correct_option")
    private String Pr_correct_option;

    public ProgressTestModal(String pr_QuestionId, String pr_Question, String pr_option1, String pr_option2, String pr_option3, String pr_option4, String pr_correct_option) {
        Pr_QuestionId = pr_QuestionId;
        Pr_Question = pr_Question;
        Pr_option1 = pr_option1;
        Pr_option2 = pr_option2;
        Pr_option3 = pr_option3;
        Pr_option4 = pr_option4;
        Pr_correct_option = pr_correct_option;
    }

    public String getPr_QuestionId() {
        return Pr_QuestionId;
    }

    public void setPr_QuestionId(String pr_QuestionId) {
        Pr_QuestionId = pr_QuestionId;
    }

    public String getPr_Question() {
        return Pr_Question;
    }

    public void setPr_Question(String pr_Question) {
        Pr_Question = pr_Question;
    }

    public String getPr_option1() {
        return Pr_option1;
    }

    public void setPr_option1(String pr_option1) {
        Pr_option1 = pr_option1;
    }

    public String getPr_option2() {
        return Pr_option2;
    }

    public void setPr_option2(String pr_option2) {
        Pr_option2 = pr_option2;
    }

    public String getPr_option3() {
        return Pr_option3;
    }

    public void setPr_option3(String pr_option3) {
        Pr_option3 = pr_option3;
    }

    public String getPr_option4() {
        return Pr_option4;
    }

    public void setPr_option4(String pr_option4) {
        Pr_option4 = pr_option4;
    }

    public String getPr_correct_option() {
        return Pr_correct_option;
    }

    public void setPr_correct_option(String pr_correct_option) {
        Pr_correct_option = pr_correct_option;
    }
}
