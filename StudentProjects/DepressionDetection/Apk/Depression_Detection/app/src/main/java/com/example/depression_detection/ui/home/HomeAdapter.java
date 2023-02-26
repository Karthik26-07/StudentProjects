package com.example.depression_detection.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depression_detection.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList Images;
    ArrayList<String> Doctor;
    ArrayList<String> Test;
    ArrayList<String> Suggestion;
    ArrayList<String> Checked;
    Context context;

    public HomeAdapter(ArrayList images, ArrayList<String> doctor, ArrayList<String> test, ArrayList<String> suggestion, ArrayList<String> checked, Context context) {
        this.Images = images;
        this.Doctor = doctor;
        this.Test = test;
        this.Suggestion = suggestion;
        this.Checked = checked;
        this.context = context;
    }


    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewmodal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       if(Checked.get(position).contentEquals("Checked")){
//           holder.doctorname.setBackgroundColor(holder.doctorname.getContext().getResources().getColor(R.id));
           holder.checking.setBackgroundColor(Color.GREEN);
       }else {
           holder.checking.setBackgroundColor(Color.RED);

       }

        holder.images.setImageResource((Integer) Images.get(position));
        holder.doctorname.setText( Doctor.get(position));
        holder.suggest.setText(Suggestion.get(position));
        holder.maketest.setText(Test.get(position));
        holder.checking.setText(Checked.get(position));
//        Toast.makeText(context, (Doctor.get(position), Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return Images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorname, suggest, maketest;
        ImageView images;
        Button checking;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorname = itemView.findViewById(R.id.name);
            maketest = itemView.findViewById(R.id.test);
            suggest = itemView.findViewById(R.id.suggestion);
            images = itemView.findViewById(R.id.images);
            checking = itemView.findViewById(R.id.check);
        }
    }


}
