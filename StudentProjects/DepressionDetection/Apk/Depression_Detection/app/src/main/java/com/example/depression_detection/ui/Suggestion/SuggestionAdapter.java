package com.example.depression_detection.ui.Suggestion;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.depression_detection.Modal.ProgressTestModal;
import com.example.depression_detection.Modal.SuggestionModal;
import com.example.depression_detection.R;

import java.util.List;

public class SuggestionAdapter extends BaseAdapter {
    Context context;
    List<SuggestionModal> suggestionModals;


    LayoutInflater inflater;


    public SuggestionAdapter(Context Context, List<SuggestionModal> suggestionModals) {
        this.context = Context;
        this.suggestionModals = suggestionModals;

        inflater = (LayoutInflater.from(Context));
    }


    @Override
    public int getCount() {
        return suggestionModals.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.suggetion_modal, null);
        TextView title=view.findViewById(R.id.suggestions_Title);
        TextView suggest = (TextView) view.findViewById(R.id.suggestions);
        title.setText(suggestionModals.get(position).getTitle());
        suggest.setText(suggestionModals.get(position).getSuggestion());

         Button more = view.findViewById(R.id.txtLink);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Suggestion")
                        .setMessage(suggestionModals.get(position).getSuggestion());

                alertDialog.setNegativeButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();
            }
        });

        return view;
    }
}
