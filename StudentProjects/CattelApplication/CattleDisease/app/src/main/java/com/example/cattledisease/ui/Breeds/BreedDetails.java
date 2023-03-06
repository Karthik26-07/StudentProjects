package com.example.cattledisease.ui.Breeds;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cattledisease.R;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class BreedDetails extends AppCompatActivity {
    TextView BreedName, Cost, Description, Food, Origin, Lifetime;
    ImageView breed_image;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = (int) getIntent().getSerializableExtra("position");
        setContentView(R.layout.breeds_detailsmodal);
        BreedName = findViewById(R.id.breedNameid);
        Cost = findViewById(R.id.breed_cost);
        Description = findViewById(R.id.description);
        Food = findViewById(R.id.foodDetails);
        Origin = findViewById(R.id.breed_origin);
        Lifetime = findViewById(R.id.Lifetime);
        breed_image = findViewById(R.id.breed_image);

        byte[] bytes = Base64.getDecoder().decode(BreedsAdapter.breedsModals.get(position).getImage());
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Bitmap image = BitmapFactory.decodeStream(bis);
        breed_image.setImageBitmap(image);

        BreedName.setText(BreedsAdapter.breedsModals.get(position).getName());
        Cost.setText(BreedsAdapter.breedsModals.get(position).getCost());
        Description.setText(BreedsAdapter.breedsModals.get(position).getDescription());
        Food.setText(BreedsAdapter.breedsModals.get(position).getFood());
        Origin.setText(BreedsAdapter.breedsModals.get(position).getOrigin());
        Lifetime.setText(BreedsAdapter.breedsModals.get(position).getLifetime());







    }


}
