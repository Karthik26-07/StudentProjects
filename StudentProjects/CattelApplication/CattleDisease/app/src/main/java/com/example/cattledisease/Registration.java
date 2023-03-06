package com.example.cattledisease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cattledisease.api.Api;
import com.example.cattledisease.model.AccountsModel;
import com.example.cattledisease.model.RegistraionModal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static androidx.core.content.ContextCompat.startActivity;

public class Registration extends AppCompatActivity {
    private EditText name, username, password, email, phone, razor_pay_api;
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Create A New Account");

        name = (EditText) findViewById(R.id.registration_name);
        username = (EditText) findViewById(R.id.registration_username);
        password = (EditText) findViewById(R.id.registration_password);
        email = (EditText) findViewById(R.id.registration_email);
        phone = (EditText) findViewById(R.id.registration_contact);


        // Registration Action
        Button registration_btn = (Button) findViewById(R.id.registration_savebtn);
        registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isValid = regValidation();
                if (isValid) {
                    if (ContextCompat.checkSelfPermission(Registration.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) Registration.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                    } else {
                        createAccount(
                                name.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString(),
                                phone.getText().toString()
                        );
                    }
                }
            }

        });

        // GoTo Login Page
        Button go_to_login = (Button) findViewById(R.id.registration_loginbtn);
        go_to_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean regValidation() {
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "Name is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.length() == 0) {
            Toast.makeText(getApplicationContext(), "Username is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Password is Required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() < 10) {
            Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    private void createAccount(String name, String username, String password, String email, String phone) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        AccountsModel model = new AccountsModel(name, username, password, email, phone);
        Call<List<RegistraionModal>> call = api.createAccount(
                model.getName(),
                model.getUsername(),
                model.getPassword(),
                model.getEmail(),
                model.getPhone()
        );
        call.enqueue(new Callback<List<RegistraionModal>>() {
            @Override
            public void onResponse(Call<List<RegistraionModal>> call, Response<List<RegistraionModal>> response) {
//        Toast.makeText(Registration.this,response.body().get().getMessage(),Toast.LENGTH_SHORT).show();
                for (int i = 0; i <= response.body().size(); i++) {
                    if (response.body().get(i).getMessage().equals("Username Already Exists")) {
                        Toast.makeText(Registration.this, response.body().get(i).getMessage(), Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Registration.this, response.body().get(i).getMessage(), Toast.LENGTH_SHORT).show();
//                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//                sendIntent.setData(Uri.parse("sms:" + response.body().get(i).getPhone()));
//                sendIntent.putExtra("sms_body", "Username:"+ response.body().get(i).getUsername()
//                       );
//                startActivity(sendIntent);
                        String msg = "Username: " + response.body().get(i).getUsername();
                        String newl = "\n";
                        String Password = "Password: " + response.body().get(i).getPassword();
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(response.body().get(i).getPhone(), null, msg + newl + Password
                                , null, null);

//                Toast.makeText(getApplicationContext(), "Message Sent",
//                        Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Registration.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RegistraionModal>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // When Back Arrow Is Pressed
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}