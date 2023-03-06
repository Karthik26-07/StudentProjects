package com.example.cattledisease.ui.Breeds;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cattledisease.R;
import com.example.cattledisease.model.BreedsModal;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.ViewHolder> {
    static List<BreedsModal> breedsModals;
    Context context;

    public BreedsAdapter(List<BreedsModal> breedsModals, Context context) {
        this.breedsModals = breedsModals;
        this.context = context;
    }

    @NonNull
    @Override
    public BreedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breeds_modal, parent, false);
        return new BreedsAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull BreedsAdapter.ViewHolder holder, int position) {
        String Image = breedsModals.get(position).getImage();
        byte[] bytes = Base64.getDecoder().decode(Image);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Bitmap image = BitmapFactory.decodeStream(bis);
        holder.BreedIcon.setImageBitmap(image);
        holder.BreedName.setText(breedsModals.get(position).getName());
        holder.BreedCost.setText(breedsModals.get(position).getCost()+" Rs");
        holder.Modal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BreedDetails.class);
                intent.putExtra("position", position);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return breedsModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView Modal;
        ImageView BreedIcon;
        TextView BreedName, BreedCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Modal = itemView.findViewById(R.id.Modal);
            BreedIcon = itemView.findViewById(R.id.imvCircular);
            BreedName = itemView.findViewById(R.id.breedName);
            BreedCost = itemView.findViewById(R.id.breedCost);
        }
    }
}
