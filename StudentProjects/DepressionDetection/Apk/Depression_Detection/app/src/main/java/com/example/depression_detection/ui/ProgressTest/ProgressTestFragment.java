package com.example.depression_detection.ui.ProgressTest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depression_detection.Api.Api;
import com.example.depression_detection.Modal.ProgressTestModal;
import com.example.depression_detection.R;
import com.example.depression_detection.ui.DepressionTest.DepressionTestAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProgressTestFragment extends Fragment {

    private static final int MODE_PRIVATE = Context.MODE_PRIVATE;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProgressTestAdapter progressTestAdapter;
    static SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_progress_test, container, false);

        recyclerView=root.findViewById(R.id.recyclerViews);
        sharedPreferences= getContext().getSharedPreferences("loginPref", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        getData(userId);


        return root;
    }

    private void getData(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<ProgressTestModal>> call=api.getProgressQuestions(userId);
        call.enqueue(new Callback<List<ProgressTestModal>>() {
            @Override
            public void onResponse(Call<List<ProgressTestModal>> call, Response<List<ProgressTestModal>> response) {
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                progressTestAdapter = new ProgressTestAdapter(response.body(),getContext());
                recyclerView.setAdapter(progressTestAdapter);
            }

            @Override
            public void onFailure(Call<List<ProgressTestModal>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}