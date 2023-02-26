package com.example.depression_detection.ui.DepressionTest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.depression_detection.Api.Api;
import com.example.depression_detection.Modal.DepressionTestAnsModal;
import com.example.depression_detection.Modal.DepressionTestModal;
import com.example.depression_detection.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DepressionTestAdapter extends RecyclerView.Adapter<DepressionTestAdapter.ViewHolder> {
    int count = 0;
    List<DepressionTestModal> depressionTestModals;
    Context context;
    ArrayList<String> dataList = new ArrayList<>();
    String radio;
    String CorrectAns;
//    int array[] = new int[]{};

    public DepressionTestAdapter(List<DepressionTestModal> depressionTestModals, Context context) {
        this.depressionTestModals = depressionTestModals;
        this.context = context;
    }

    @NonNull
    @Override
    public DepressionTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.depressiontest_modal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepressionTestAdapter.ViewHolder holder, int position) {

//        int index = (int) (Math.random() * depressionTestModals.size());
        int index = depressionTestModals.size();

        holder.Question.setText(depressionTestModals.get(count).getQuestion());
        holder.option1.setText(depressionTestModals.get(count).getOption1());
        holder.option2.setText(depressionTestModals.get(count).getOption2());
//        holder.option3.setText(depressionTestModals.get(count).getOption3());
//        holder.option4.setText(depressionTestModals.get(count).getOption4());


        holder.Next_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOption = holder.radioGroup.getCheckedRadioButtonId();
//                String Answer = depressionTestModals.get(count).getCorrectAns();
                count++;
                if (count < (index - 1)) {
                    customDisplay(selectedOption);
                } else {
                    holder.Next_Question.setVisibility(View.INVISIBLE);
                    holder.check.setVisibility(View.VISIBLE);

                    customDisplay(selectedOption);

                    JSONArray jsonArray = new JSONArray(dataList);
                    String dataToSend = jsonArray.toString();
//                    Toast.makeText(context, "Please wait for while", Toast.LENGTH_SHORT).show();
                    holder.check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Please wait for while", Toast.LENGTH_SHORT).show();

                            Gson gson = new GsonBuilder()
                                    .setLenient()
                                    .create();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Api.ROOT_URL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build();
                            Api api = retrofit.create(Api.class);
                            Call<List<DepressionTestAnsModal>> call = api.getAnswer(dataToSend);
                            call.enqueue(new Callback<List<DepressionTestAnsModal>>() {
                                @Override
                                public void onResponse(Call<List<DepressionTestAnsModal>> call, Response<List<DepressionTestAnsModal>> response) {
                                    Toast.makeText(context, response.body().get(0).response, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<List<DepressionTestAnsModal>> call, Throwable t) {
                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    });
                }
            }

            private void customDisplay(int selectedOption) {
                if (selectedOption == -1) {
                    Toast.makeText(context, "Please answer the question", Toast.LENGTH_SHORT).show();

                } else {
                    switch (selectedOption) {
                        case R.id.option1:
                            radio = "Yes";
                            break;
                        case R.id.option2:
                            radio = "No";
                            break;
//                        case R.id.option3:
//                            radio = "3";
//                            break;
//                        case R.id.option4:
//                            radio = "4";
//                            break;

                    }
//                    if (Answer.equals(radio)) {
//                        CorrectAns = "1";
//                    } else {
//                        CorrectAns = "0";
//                    }
//                    Toast.makeText(context, Answer, Toast.LENGTH_SHORT).show();

                    dataList.add(radio);

                    holder.radioGroup.clearCheck();
                    holder.Question.setText(depressionTestModals.get(count).getQuestion());
                    holder.option1.setText(depressionTestModals.get(count).getOption1());
                    holder.option2.setText(depressionTestModals.get(count).getOption2());
//                    holder.option3.setText(depressionTestModals.get(count).getOption3());
//                    holder.option4.setText(depressionTestModals.get(count).getOption4());

                }

            }


        });


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Question;
        RadioButton option1, option2, option3, option4;
        Button Next_Question, check;
        RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Question = itemView.findViewById(R.id.question);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
//            option3 = itemView.findViewById(R.id.option3);
//            option4 = itemView.findViewById(R.id.option4);
            Next_Question = itemView.findViewById(R.id.Next_question);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            check = itemView.findViewById(R.id.Next_check);

        }
    }
}
