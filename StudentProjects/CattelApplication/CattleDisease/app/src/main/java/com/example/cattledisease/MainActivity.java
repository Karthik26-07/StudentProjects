package com.example.cattledisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cattledisease.api.Api;
//import com.example.cattledisease.model.AccountsModel;
//import com.example.cattledisease.model.UserModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    private TextInputEditText username, password;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.login_contact);
        password = findViewById(R.id.login_password);



        sharedPreferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        checkLogin();

        TextView textView = (TextView) findViewById(R.id.forgot_password);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
//                startActivity(intent);
            }
        });

        // Login Action
        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValid = loginValidation();
                if(isValid){
                    loginAccount(
                            username.getText().toString(), password.getText().toString()
                    );
                }
            }
        });

        // GoTo Registration Page
        Button go_to_registration = (Button) findViewById(R.id.go_to_registration);
        go_to_registration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }



    private boolean loginValidation() {
        if(username.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(), "Username is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(), "Password is Required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void loginAccount(String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);


        Call<String> call = api.loginAccount(username,password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String res = response.body();

                if (res.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Login Sucsessful", Toast.LENGTH_SHORT).show();

//                    getUserDetails(username);

                    editor.putBoolean("isLogin", true);
                    editor.putString("username",username);
                    editor.commit();



                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);

                        finish();


                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}