package com.example.assignmenta2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categoryToAdapt;

    public void setData(List<Category> categoryToAdapt) {
        this.categoryToAdapt = categoryToAdapt;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category, parent, false);

        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        final Category categoryAtPosition = categoryToAdapt.get(position);
        final Context context = holder.view.getContext();
        holder.bind(categoryAtPosition);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(context, TriviaRecycler.class);
                intent1.putExtra("category", categoryAtPosition.getId());
                context.startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryToAdapt.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView categoryText;
        public TextView cluesc;

        public CategoriesViewHolder(View v) {
            super(v);
            view = v;
            categoryText = v.findViewById(R.id.categoryt);
            cluesc = v.findViewById(R.id.cluescount);
        }

        public void bind(final Category category) {

            String categoryname = category.getTitle();
            String cap = categoryname.substring(0, 1).toUpperCase() + categoryname.substring(1);
            categoryText.setText(cap);
            cluesc.setText("Number of Questions: " + category.getClues_count());
        }
    }

}
