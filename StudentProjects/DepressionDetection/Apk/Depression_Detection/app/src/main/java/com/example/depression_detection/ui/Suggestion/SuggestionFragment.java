package com.example.depression_detection.ui.Suggestion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.depression_detection.Api.Api;
import com.example.depression_detection.Modal.SuggestionModal;
import com.example.depression_detection.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SuggestionFragment extends Fragment {
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragement_suggestion, container, false);
        listView = root.findViewById(R.id.suggestion_list);
        getData();


        return root;

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<SuggestionModal>> call = api.getSuggestion();
        call.enqueue(new Callback<List<SuggestionModal>>() {
            @Override
            public void onResponse(Call<List<SuggestionModal>> call, Response<List<SuggestionModal>> response) {
//                Toast.makeText(getContext(), "reached", Toast.LENGTH_SHORT).show();
                for (int i=0; i<response.body().size(); i++) {

                    SuggestionAdapter suggestionAdapter=new SuggestionAdapter(getContext(),response.body());
                    listView.setAdapter(suggestionAdapter);




                }
            }

            @Override
            public void onFailure(Call<List<SuggestionModal>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
