package com.example.assignmenta2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TriviaViewHolder> {
    public static List<Trivia> triviaToAdapt;
    public static int score = 0;

    public void setData(List<Trivia> triviaToAdapt) {
        this.triviaToAdapt = triviaToAdapt;
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.trivia, parent, false);

        TriviaViewHolder triviaViewHolder = new TriviaViewHolder(view);
        return triviaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {
        final Trivia triviaAtPosition = triviaToAdapt.get(position);
        final Context context = holder.view.getContext();
        holder.bind(triviaAtPosition);
    }

    @Override
    public int getItemCount() {
        return triviaToAdapt.size();
    }

    public static class TriviaViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView questionText;
        public TextView answert;
        public ImageButton answerb;
        public ImageButton wrongt;
        public TextView wrongp;
        public TextView value;
        public TextView heading;
        public Image googleview;

        public TriviaViewHolder(View v){
            super(v);
            view = v;
            questionText = v.findViewById(R.id.questionText);
            answert = v.findViewById(R.id.answertext);
            answerb = v.findViewById(R.id.answerbutton);
            wrongt = v.findViewById(R.id.wrong);
            wrongp = v.findViewById(R.id.wrongprompt);
            value = v.findViewById(R.id.valuetext);
            heading = v.findViewById(R.id.questionn);
        }
        public void bind(final Trivia trivia) {

            questionText.setText(trivia.getQuestion());
            value.setText("Score/Value: " + Integer.toString(trivia.getValue()));

            answerb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1 = trivia.getAnswer();
                    String replaceString = s1.replace("<i>","");
                    String s2 = replaceString;
                    String replaceString3 = s2.replace("</i>", "");
                    String cap = replaceString3.substring(0, 1).toUpperCase() + replaceString3.substring(1);
                    answert.setText(cap);
                    answert.setTextColor(Color.GREEN);
                    score += trivia.getValue();
                    Toast.makeText(v.getContext(), "Your Current Score Is: " + String.valueOf(score) + "!", Toast.LENGTH_SHORT).show();
                }
            });

            wrongt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s1 = trivia.getAnswer();
                    String replaceString = s1.replace("<i>","");
                    String s2 = replaceString;
                    String replaceString3 = s2.replace("</i>", "");
                    String cap = replaceString3.substring(0, 1).toUpperCase() + replaceString3.substring(1);
                    answert.setText(cap);
                    answert.setTextColor(Color.RED);
                    wrongp.setText("Still don't get the right answer? Click me to find out more!");
                    wrongp.setVisibility(View.VISIBLE);
                    wrongp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + trivia.getQuestion()));
                            v.getContext() .startActivity(implicit);
                        }
                    });
                }
            });

        }
    }
}
