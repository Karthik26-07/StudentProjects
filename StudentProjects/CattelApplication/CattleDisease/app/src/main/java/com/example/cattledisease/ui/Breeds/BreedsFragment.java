package com.example.cattledisease.ui.Breeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cattledisease.R;
import com.example.cattledisease.api.Api;
import com.example.cattledisease.model.BreedsModal;
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

public class BreedsFragment extends Fragment {
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    BreedsAdapter breedsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragement_breeds, container, false);
        recyclerView=root.findViewById(R.id.recyclerView);

        getData();


        return root;
    }

    private void getData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<BreedsModal>> call=api.getBreeds();
        call.enqueue(new Callback<List<BreedsModal>>() {
            @Override
            public void onResponse(@NotNull Call<List<BreedsModal>> call, @NotNull Response<List<BreedsModal>> response) {
                linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                breedsAdapter = new BreedsAdapter(response.body(), getContext());
                recyclerView.setAdapter(breedsAdapter);
            }

            @Override
            public void onFailure(Call<List<BreedsModal>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
