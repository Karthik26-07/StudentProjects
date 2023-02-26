package com.example.depression_detection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.depression_detection.Api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    ImageView back;
    TextView name, username, password, email, phone, DateOfBirth;
    Button Register;
    boolean isValid = false;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.registration_name);
        username = findViewById(R.id.registration_username);
        password = findViewById(R.id.registration_password);
        email = findViewById(R.id.registration_email);
        phone = findViewById(R.id.registration_contact);
        DateOfBirth = findViewById(R.id.registration_dob);
        Register = findViewById(R.id.reg_button);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }


            private void updateLabel() {
                String myFormat = "YYYY - MM - dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                DateOfBirth.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        DateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegistrationActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        back = findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValid = regValidation();
                if (isValid) {
                    createAccount(
                            name.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString(),
                            email.getText().toString(),
                            phone.getText().toString(),
                            DateOfBirth.getText().toString()

                    );
                }
            }

            private void createAccount(String Name, String UserName, String Password, String Email, String Phone, String Date) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.ROOT_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                Api api = retrofit.create(Api.class);
                Call<String> call = api.createAccount(Name, UserName, Password, Email, Phone, Date);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("Account Created Successfully")) {
                            Toast.makeText(RegistrationActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegistrationActivity.this, response.body(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
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
}