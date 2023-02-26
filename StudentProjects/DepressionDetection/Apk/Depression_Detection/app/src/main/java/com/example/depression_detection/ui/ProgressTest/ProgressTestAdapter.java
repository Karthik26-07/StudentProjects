package com.example.depression_detection.ui.ProgressTest;

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
import com.example.depression_detection.Modal.DepressionTestModal;
import com.example.depression_detection.Modal.ProgressTestAnsModal;
import com.example.depression_detection.Modal.ProgressTestModal;
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


public class ProgressTestAdapter extends RecyclerView.Adapter<ProgressTestAdapter.ViewHolder> {
    List<ProgressTestModal> progressTestModals;
    Context context;
    int Count = 0;
    ArrayList<String> dataList = new ArrayList<>();
    String radio;

    public ProgressTestAdapter(List<ProgressTestModal> progressTestModals, Context context) {
        this.progressTestModals = progressTestModals;
        this.context = context;
    }

    @NonNull
    @Override
    public ProgressTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_test_modal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressTestAdapter.ViewHolder holder, int position) {
        int index = progressTestModals.size();
        holder.Question.setText(progressTestModals.get(Count).getPr_Question());
        holder.option1.setText(progressTestModals.get(Count).getPr_option1());
        holder.option2.setText(progressTestModals.get(Count).getPr_option2());
//        holder.option3.setText(progressTestModals.get(Count).getPr_option3());
//        holder.option4.setText(progressTestModals.get(Count).getPr_option4());
        holder.Next_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOption = holder.radioGroup.getCheckedRadioButtonId();
//                String Answer = progressTestModals.get(Count).getPr_correct_option();

                Count++;
                if (Count < (index - 1)) {
                    customDisplay(selectedOption);
                } else {
                    holder.Next_Question.setVisibility(View.INVISIBLE);
                    holder.check.setVisibility(View.VISIBLE);

                    customDisplay(selectedOption);

                    JSONArray jsonArray = new JSONArray(dataList);
                    String dataToSend = jsonArray.toString();
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
                            Call<List<ProgressTestAnsModal>> call = api.getProgressAnswer(dataToSend);
                            call.enqueue(new Callback<List<ProgressTestAnsModal>>() {
                                @Override
                                public void onResponse(Call<List<ProgressTestAnsModal>> call, Response<List<ProgressTestAnsModal>> response) {
                                    Toast.makeText(context, response.body().get(0).response, Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(Call<List<ProgressTestAnsModal>> call, Throwable t) {
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
                    holder.Question.setText(progressTestModals.get(Count).getPr_Question());
                    holder.option1.setText(progressTestModals.get(Count).getPr_option1());
                    holder.option2.setText(progressTestModals.get(Count).getPr_option2());
//        holder.option3.setText(progressTestModals.get(Count).getPr_option3());
//        holder.option4.setText(progressTestModals.get(Count).getPr_option4());

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
            Question = itemView.findViewById(R.id.pr_question);
            option1 = itemView.findViewById(R.id.pr_option1);
            option2 = itemView.findViewById(R.id.pr_option2);
//            option3 = itemView.findViewById(R.id.pr_option3);
//            option4 = itemView.findViewById(R.id.pr_option4);
            Next_Question = itemView.findViewById(R.id.pr_Next_question);
            radioGroup = itemView.findViewById(R.id.Pr_radioGroup);
            check = itemView.findViewById(R.id.Pr_Next_check);
        }
    }
}
