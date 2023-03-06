package com.example.cattledisease.ui.Symptoms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cattledisease.R;
import com.example.cattledisease.api.Api;
import com.example.cattledisease.model.SymptomListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SymptomsFragements extends Fragment {
    private SymptomListModel mViewModel;

    ArrayList<SymptomListModel> skinSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> breathingSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> muscleSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> foodSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> psychologicalSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> temperatureSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> calvingSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> nasalSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> eyeSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> body_conditionSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> movementSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> milk_productionSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> mouthSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> commonSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> dug_and_urinSymptomList = new ArrayList<>();
    ArrayList<SymptomListModel> otherSymptomList = new ArrayList<>();

    ArrayList<String> selectedList = new ArrayList<>();

    String[] skin = {
            "Non-pigmented skin",
            "Hair loss",
            "peeling",
            "Crusting",
            "bleeding",
            "reddening",
            "skin wound",
            "stright body hair",
            "loss of hair",
            "skin damage",
            "white keratin",
            "scab",
            "Rubbing",
            "Biting",
            "Scratching",
            "itching",
            "circular patch",
            "Grey skin",
            "Paleness",
    };

    String[] breathing = {
            "Respiratory distress",
            "Chronic coughing",
            "Coughing",
            "Raised breathing",
            "breathing difficult",
            "Respiratory distress",
            "Chronic coughing",
            "mild cough",
    };

    String[] muscle = {
            "muscel Twitching ",
            "musle movements",
            "no musle movements",
            "spasm",
    };

    String[] food = {
            "poor appetite",
            "no food intake",
            "swallowing difficult",
            "filled stomach",
            "soil licking",
            "Reduced appetite",
            "lack of appetite",
            "Inability to eat",
            "Inability to drink",
            "swallowing difficulty",
            "no food intake"
    };

    String[] psychological = {
            "anxious",
            "Dull and depressed",
            "Anxiety",
            "Depression",
            "nervous",
            "agression",
    };

    String[] temperature = {
            "High temperature",
            "no temperature",
            "cold",
            "low temperature",
    };

    String[] calving = {
            "Stillborn",
            "Weak calf",
            "infected membranes",
            "fetal membranes",
            "Infertility",
            "Abortion",
            "Reduced fertility",
            "Reduced fertility",
    };

    String[] nasal = {
            "nasal tissue",
            "nasal discharge",
            "red nasal discharge",
            "bloody nasal discharge",
    };

    String[] eye = {
            "runny eyes",
            "red conjunctiva",
            "corneal ulcers",
    };

    String[] body_condition = {
            "trembling",
            "convulsion",
            "stiff held out tail",
            "collapse",
            "stiff legs",
            "Tremors",
            "Stumbling",
            "Lethargy",
            "failed to stand",
            "Poor body condition",
            "shaking foot",
            "Lameness",
            "Drooling",
            "loss of condition",
            "Seizures",
            "Unsteady gait",
            "wobbling",
            "falling over",
            "Tremors",
            "Stumbling",
            "head down",
    };

    String[] movement = {
            "Stiffness to move",
            "Unwillingness to move",
            "stagger",
    };

    String[] milk_production = {
            "decreased milk production",
            "yellow milk",
            "blisters on teats",
            "enlarged udder",
            "sudden milk drop",
    };

    String[] mouth = {
            "Lockjaw",
            "salivation",
            "dry lips",
            "no chewing",
            "excessive salivation",
            "swollen lips",
            "swollen tongue",
            "inflamed mouths",
            "swollen cheek",
            "foul-smelling mouth",
            "Swollen pharyngeal region",
            "ulcers on the tongue",
            "Bilsters in mouth",
            "Bilsters on mouth",
            "Quivering lips",
            "mouth frothing",
            "Painful tongue",
            "swollen tongue",
            "tongue Ulcer",
    };

    String[] common = {
            "weakness",
            "fever",
            "mild fever",
            "Weight loss",
            "strange voice",
            "frequent sleeping",
            "stomach pain",
            "high Pulse rate",
            "high Pulse rate",
    };

    String[] dug_and_urin = {
            "Dark red urine",
            "smelly dug",
            "red urine",
            "black urine",
            "loos motion",
    };

    String[] other =
            {
                    "paralysis",
                    "Pneumonia",
                    "Jaundice",
                    "abdomen swelling",
                    "non-smelling diarrhoea",
                    "abdominal pain",
            };


    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10, spinner11, spinner12, spinner13, spinner14, spinner15, spinner16;

    public static SymptomsFragements newInstance() {
        return new SymptomsFragements();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_symptoms, container, false);
        spinner1 = (Spinner) root.findViewById(R.id.spinner1);
        spinner2 = (Spinner) root.findViewById(R.id.spinner2);
        spinner3 = (Spinner) root.findViewById(R.id.spinner3);
        spinner4 = (Spinner) root.findViewById(R.id.spinner4);
        spinner5 = (Spinner) root.findViewById(R.id.spinner5);
        spinner6 = (Spinner) root.findViewById(R.id.spinner6);
        spinner7 = (Spinner) root.findViewById(R.id.spinner7);
        spinner8 = (Spinner) root.findViewById(R.id.spinner8);
        spinner9 = (Spinner) root.findViewById(R.id.spinner9);
        spinner10 = (Spinner) root.findViewById(R.id.spinner10);
        spinner11 = (Spinner) root.findViewById(R.id.spinner11);
        spinner12 = (Spinner) root.findViewById(R.id.spinner12);
        spinner13 = (Spinner) root.findViewById(R.id.spinner13);
        spinner14 = (Spinner) root.findViewById(R.id.spinner14);
        spinner15 = (Spinner) root.findViewById(R.id.spinner15);
        spinner16 = (Spinner) root.findViewById(R.id.spinner16);

        for (int i=0;i<skin.length;i++){
            skinSymptomList.add(new SymptomListModel(skin[i],false));
        }
        for (int i=0;i<breathing.length;i++){
            breathingSymptomList.add(new SymptomListModel(breathing[i],false));
        }
        for (int i=0;i<muscle.length;i++){
            muscleSymptomList.add(new SymptomListModel(muscle[i],false));
        }
        for (int i=0;i<food.length;i++){
            foodSymptomList.add(new SymptomListModel(food[i],false));
        }
        for (int i=0;i<psychological.length;i++){
            psychologicalSymptomList.add(new SymptomListModel(psychological[i],false));
        }
        for (int i=0;i<temperature.length;i++){
            temperatureSymptomList.add(new SymptomListModel(temperature[i],false));
        }
        for (int i=0;i<calving.length;i++){
            calvingSymptomList.add(new SymptomListModel(calving[i],false));
        }
        for (int i=0;i<nasal.length;i++){
            nasalSymptomList.add(new SymptomListModel(nasal[i],false));
        }
        for (int i=0;i<eye.length;i++){
            eyeSymptomList.add(new SymptomListModel(eye[i],false));
        }
        for (int i=0;i<body_condition.length;i++){
            body_conditionSymptomList.add(new SymptomListModel(body_condition[i],false));
        }
        for (int i=0;i<movement.length;i++){
            movementSymptomList.add(new SymptomListModel(movement[i],false));
        }
        for (int i=0;i<milk_production.length;i++){
            milk_productionSymptomList.add(new SymptomListModel(milk_production[i],false));
        }
        for (int i=0;i<mouth.length;i++){
            mouthSymptomList.add(new SymptomListModel(mouth[i],false));
        }
        for (int i=0;i<common.length;i++){
            commonSymptomList.add(new SymptomListModel(common[i],false));
        }
        for (int i=0;i<dug_and_urin.length;i++){
            dug_and_urinSymptomList.add(new SymptomListModel(dug_and_urin[i],false));
        }
        for (int i=0;i<other.length;i++){
            otherSymptomList.add(new SymptomListModel(other[i],false));
        }

        SymptomListAdapter adapter1 = new SymptomListAdapter(getContext(),skinSymptomList);
        spinner1.setAdapter(adapter1);

        SymptomListAdapter adapter2 = new SymptomListAdapter(getContext(),breathingSymptomList);
        spinner2.setAdapter(adapter2);

        SymptomListAdapter adapter3 = new SymptomListAdapter(getContext(),muscleSymptomList);
        spinner3.setAdapter(adapter3);

        SymptomListAdapter adapter4 = new SymptomListAdapter(getContext(),foodSymptomList);
        spinner4.setAdapter(adapter4);

        SymptomListAdapter adapter5 = new SymptomListAdapter(getContext(),psychologicalSymptomList);
        spinner5.setAdapter(adapter5);

        SymptomListAdapter adapter6 = new SymptomListAdapter(getContext(),temperatureSymptomList);
        spinner6.setAdapter(adapter6);

        SymptomListAdapter adapter7 = new SymptomListAdapter(getContext(),calvingSymptomList);
        spinner7.setAdapter(adapter7);

        SymptomListAdapter adapter8 = new SymptomListAdapter(getContext(),nasalSymptomList);
        spinner8.setAdapter(adapter8);

        SymptomListAdapter adapter9 = new SymptomListAdapter(getContext(),eyeSymptomList);
        spinner9.setAdapter(adapter9);

        SymptomListAdapter adapter10 = new SymptomListAdapter(getContext(),body_conditionSymptomList);
        spinner10.setAdapter(adapter10);

        SymptomListAdapter adapter11 = new SymptomListAdapter(getContext(),movementSymptomList);
        spinner11.setAdapter(adapter11);

        SymptomListAdapter adapter12 = new SymptomListAdapter(getContext(),milk_productionSymptomList);
        spinner12.setAdapter(adapter12);

        SymptomListAdapter adapter13 = new SymptomListAdapter(getContext(),mouthSymptomList);
        spinner13.setAdapter(adapter13);

        SymptomListAdapter adapter14 = new SymptomListAdapter(getContext(),commonSymptomList);
        spinner14.setAdapter(adapter14);

        SymptomListAdapter adapter15 = new SymptomListAdapter(getContext(),dug_and_urinSymptomList);
        spinner15.setAdapter(adapter15);

        SymptomListAdapter adapter16 = new SymptomListAdapter(getContext(),otherSymptomList);
        spinner16.setAdapter(adapter16);

        Button btn_send = (Button) root.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int k=0;k<skinSymptomList.size();k++){
                    if(skinSymptomList.get(k).getSelected()){
                        selectedList.add(skinSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<breathingSymptomList.size();k++){
                    if(breathingSymptomList.get(k).getSelected()){
                        selectedList.add(breathingSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<muscleSymptomList.size();k++){
                    if(muscleSymptomList.get(k).getSelected()){
                        selectedList.add(muscleSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<foodSymptomList.size();k++){
                    if(foodSymptomList.get(k).getSelected()){
                        selectedList.add(foodSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<psychologicalSymptomList.size();k++){
                    if(psychologicalSymptomList.get(k).getSelected()){
                        selectedList.add(psychologicalSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<temperatureSymptomList.size();k++){
                    if(temperatureSymptomList.get(k).getSelected()){
                        selectedList.add(temperatureSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<calvingSymptomList.size();k++){
                    if(calvingSymptomList.get(k).getSelected()){
                        selectedList.add(calvingSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<nasalSymptomList.size();k++){
                    if(nasalSymptomList.get(k).getSelected()){
                        selectedList.add(nasalSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<eyeSymptomList.size();k++){
                    if(eyeSymptomList.get(k).getSelected()){
                        selectedList.add(eyeSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<body_conditionSymptomList.size();k++){
                    if(body_conditionSymptomList.get(k).getSelected()){
                        selectedList.add(body_conditionSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<movementSymptomList.size();k++){
                    if(movementSymptomList.get(k).getSelected()){
                        selectedList.add(movementSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<milk_productionSymptomList.size();k++){
                    if(milk_productionSymptomList.get(k).getSelected()){
                        selectedList.add(milk_productionSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<mouthSymptomList.size();k++){
                    if(mouthSymptomList.get(k).getSelected()){
                        selectedList.add(mouthSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<commonSymptomList.size();k++){
                    if(commonSymptomList.get(k).getSelected()){
                        selectedList.add(commonSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<dug_and_urinSymptomList.size();k++){
                    if(dug_and_urinSymptomList.get(k).getSelected()){
                        selectedList.add(dug_and_urinSymptomList.get(k).getName());
                    }
                }
                for(int k=0;k<otherSymptomList.size();k++){
                    if(otherSymptomList.get(k).getSelected()){
                        selectedList.add(otherSymptomList.get(k).getName());
                    }
                }

                System.out.println(selectedList);
                symptomBasedPrediction(selectedList);
            }

            private void symptomBasedPrediction(ArrayList<String> selectedList) {

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.ROOT_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                Api api = retrofit.create(Api.class);
                Call<String> call = api.symptomBasedPrediction(selectedList);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("Predection Result")
                                .setMessage("Disease: \n"+response.body());

                        alertDialog.setNegativeButton("DONE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        alertDialog.show();
                        selectedList.clear();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        selectedList.clear();
                    }
                });



            }
        });




        return root;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(SymptomListModel.class);
        // TODO: Use the ViewModel
    }
}