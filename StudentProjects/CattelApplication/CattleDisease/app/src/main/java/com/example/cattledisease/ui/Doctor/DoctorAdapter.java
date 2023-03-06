package com.example.cattledisease.ui.Doctor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cattledisease.R;
import com.example.cattledisease.model.DoctorsModel;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    List<DoctorsModel> doctor_model;
    Context context;

    public DoctorAdapter(List<DoctorsModel> doctor_model, Context context) {
        this.doctor_model = doctor_model;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_modal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        holder.Name.setText(doctor_model.get(position).getDname());
        holder.Letter.setText(String.valueOf(doctor_model.get(position).getDname().charAt(0)));
        holder.Call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + doctor_model.get(position).getDphone()));
                    startActivity(context, callIntent, Bundle.EMPTY);

                }

            }
        });
        holder.Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, 1);
                } else {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:" + doctor_model.get(position).getDphone()));
                    startActivity(context, sendIntent, Bundle.EMPTY);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctor_model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Letter;
        Button Call, Message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Call = itemView.findViewById(R.id.call);
            Message = itemView.findViewById(R.id.Messages);
            Letter=itemView.findViewById(R.id.my_letter);

        }
    }
}
