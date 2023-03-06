package com.example.cattledisease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        SharedPreferences sh = getSharedPreferences("loginPref", MODE_PRIVATE);
        String username = sh.getString("username", "");
        if(username.equals("")){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(this,Home.class));
            finish();
        }



    }
}