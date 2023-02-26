package com.example.depression_detection.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depression_detection.R;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {
    ArrayList Images = new ArrayList<>(Arrays.asList(R.drawable.depression1, R.drawable.deprssion2, R.drawable.depression3));
    ArrayList doctor = new ArrayList<>(Arrays.asList("Karan Sharma", "Chethan Sharma", "Pritham"));
    ArrayList suggestion = new ArrayList<>((Arrays.asList("Make your brain test regularly", "Follow the given prescription regularly", "Take Suggestion form others")));
    ArrayList test = new ArrayList<>(Arrays.asList("Depression Test", "Progress Test", "Suggestions"));
    ArrayList checked = new ArrayList<>(Arrays.asList("Checked", "Not Checked", "Checked"));

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(Images, doctor, test, suggestion, checked, getContext());
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setAdapter(homeAdapter);
        return root;
    }
}