package com.example.cattledisease.ui.Symptoms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cattledisease.R;
import com.example.cattledisease.model.SymptomListModel;

import java.util.ArrayList;

public class SymptomListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SymptomListModel> symptomListModel;

    public SymptomListAdapter(@NonNull Context context, ArrayList<SymptomListModel> symptomListModel) {
        super(context,0,symptomListModel);
        this.context = context;
        this.symptomListModel = symptomListModel;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View view,
                          ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.symptoms_list_row, parent, false);

        TextView symptoms_name = (TextView) view.findViewById(R.id.symptoms_name);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        symptoms_name.setText(symptomListModel.get(position).getName());
        checkBox.setChecked(symptomListModel.get(position).getSelected());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(symptomListModel.get(position).getSelected()){
                    symptomListModel.get(position).setSelected(false);
                }
                else{
                    symptomListModel.get(position).setSelected(true);
                }
            }
        });

        return view;
    }

}
