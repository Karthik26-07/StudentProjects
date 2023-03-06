package com.example.cattledisease.ui.UploadImage;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cattledisease.R;
import com.example.cattledisease.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UploadImageFragment extends Fragment {

    private UploadImageViewModel predictionViewModel;

    private Button select_image, upload_btn;
    private ImageView image;

    private Bitmap bitmap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        predictionViewModel =
                ViewModelProviders.of(this).get(UploadImageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_uploadimage, container, false);
//        final TextView textView = root.findViewById(R.id.text_prediction);
//        predictionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        select_image = (Button) root.findViewById(R.id.select_image);
        image = (ImageView) root.findViewById(R.id.image);
        upload_btn = (Button) root.findViewById(R.id.upload_btn);

        upload_btn.setVisibility(View.GONE);
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 105);
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 200);
                }
            }
        });


        return root;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity().getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            image.setImageURI(selectedImage);
            upload_btn.setVisibility(View.VISIBLE);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream);
            byte[] bytearray = stream.toByteArray();
            String compress = Base64.encodeToString(bytearray, Base64.DEFAULT);

            upload_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImage(compress);
                }
            });
        }
    }

    private void uploadImage(String image) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.ROOT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        Call<String> call = api.uploadImage(image);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Predection Result")
                        .setMessage(response.body());

                alertDialog.setNegativeButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
