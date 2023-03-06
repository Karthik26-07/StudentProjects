package com.example.cattledisease.api;

//import com.example.cattledisease.model.BillModel;
//import com.example.dairy.model.DiseasesModel;
//import com.example.dairy.model.DoctorsModel;
//import com.example.dairy.model.FarmersModel;
//import com.example.dairy.model.MilkRequestModel;
//import com.example.dairy.model.NotificationModel;
//import com.example.dairy.model.StatisticsModel;
//import com.example.dairy.model.UserModel;

import com.example.cattledisease.model.BreedsModal;
import com.example.cattledisease.model.DoctorsModel;
import com.example.cattledisease.model.RegistraionModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String IP = "192.168.43.21";
    String ROOT_URL = "http://" + IP + ":8000/CattleServer/";

    @GET("/register/")
    Call<List<RegistraionModal>> createAccount(
            @Query("name") String name,
            @Query("username") String username,
            @Query("password") String password,
            @Query("email") String email,
            @Query("phone") String phone
    );

    @GET("/login/")
    Call<String> loginAccount(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("/forgotpassword/")
    Call<String> getPassword(
            @Query("email") String email
    );

    @GET("/doctor/")
    Call<List<DoctorsModel>> doctor_details();

    @FormUrlEncoded
    @POST("/symptombased/")
    Call<String> symptomBasedPrediction(
            @Field("selectedList") ArrayList<String> selectedList
    );

    @FormUrlEncoded
    @POST("/prediction/")
    Call<String> uploadImage(
            @Field("image") String image
    );

    @GET("/get_breeds/")
    Call<List<BreedsModal>> getBreeds();

}
