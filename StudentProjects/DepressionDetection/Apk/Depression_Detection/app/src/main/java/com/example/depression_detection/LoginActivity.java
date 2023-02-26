package com.example.depression_detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.depression_detection.Api.Api;
import com.example.depression_detection.Modal.LoginModal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {
    ImageView backbutton;
    Button Login;
    TextView Email, Password;
    boolean isValid = false;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("loginPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        backbutton = findViewById(R.id.goto_main);
        Login = findViewById(R.id.login_button);



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValid = loginValidation();
                if (isValid) {
                    loginAccount(
                            Email.getText().toString(), Password.getText().toString()
                    );
                }


            }

            private void loginAccount(String Email, String Password) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.ROOT_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                Api api = retrofit.create(Api.class);
                Call<List<LoginModal>> call = api.loginAccount(Email, Password);
                call.enqueue(new Callback<List<LoginModal>>() {
                    @Override
                    public void onResponse(Call<List<LoginModal>> call, Response<List<LoginModal>> response) {
//
//
//
                        if (response.body().get(0).getResponse().equals("valid")) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            editor.putString("id",response.body().get(0).getId());
                            editor.apply();


                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();

                        }
                    }

//

                    @Override
                    public void onFailure(Call<List<LoginModal>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            private boolean loginValidation() {
                if (Email.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Email is Required", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (Password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Password is Required", Toast.LENGTH_SHORT).show();
                    return false;
                }

                return true;
            }
        });
    }


}