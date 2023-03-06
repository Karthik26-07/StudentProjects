package com.example.cattledisease.ui.Doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cattledisease.R;
import com.example.cattledisease.api.Api;
import com.example.cattledisease.model.DoctorsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Doctor extends Fragment {
    LinearLayoutManager linearLayoutManager;
    DoctorAdapter doctorAdapter;
    RecyclerView recyclerView;
    View View;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.doctor,container,false);
        doctor_details();
        recyclerView=View.findViewById(R.id.recyclerView);

        return View;
    }

    private void doctor_details() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<DoctorsModel>> doctor=api.doctor_details();
        doctor.enqueue(new Callback<List<DoctorsModel>>() {
            @Override
            public void onResponse(Call<List<DoctorsModel>> call, Response<List<DoctorsModel>> response) {
                linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                doctorAdapter = new DoctorAdapter(response.body(), getContext());
                recyclerView.setAdapter(doctorAdapter);
            }

            @Override
            public void onFailure(Call<List<DoctorsModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
