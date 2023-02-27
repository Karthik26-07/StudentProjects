package com.example.depression_detection.Api;


import com.example.depression_detection.Modal.DepressionTestAnsModal;
import com.example.depression_detection.Modal.DepressionTestModal;
import com.example.depression_detection.Modal.LoginModal;
import com.example.depression_detection.Modal.ProgressTestAnsModal;
import com.example.depression_detection.Modal.ProgressTestModal;
import com.example.depression_detection.Modal.SuggestionModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    String IP = "192.168.1.6";
    String ROOT_URL = "http://" + IP + ":8000/DepressionServer/";


    @FormUrlEncoded
    @POST("/register/")
    Call<String> createAccount(
            @Field("name") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("date") String date

    );

    @GET("/login/")
    Call<List<LoginModal>> loginAccount(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("/get_Questions/")
    Call<List<DepressionTestModal>> getQuestions(
            @Query("userId") String userId
    );

    @GET("/get_Answer/")
    Call<List<DepressionTestAnsModal>> getAnswer(
            @Query("Answers") String Answers
    );

    @GET("/get_Progress_Question/")
    Call<List<ProgressTestModal>> getProgressQuestions(
            @Query("userId") String userId
    );
    @GET("/get_Progress_Answer/")
    Call<List<ProgressTestAnsModal>> getProgressAnswer(
            @Query("Progress_Answers") String Progress_Answers
    );
    @GET("/GetSuggestion/")
    Call<List<SuggestionModal>> getSuggestion(
//            @Query("Progress_Answers") String Progress_Answers
    );



}
