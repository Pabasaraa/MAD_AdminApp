package com.example.kuppiya_admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    Context context;

    ArrayList<newsHelperClass> list;

    public NewsAdapter(Context context, ArrayList<newsHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.news,parent,false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        newsHelperClass newsHelperClass = list.get(position);
        holder.topic.setText(newsHelperClass.getTopic());
        holder.description.setText(newsHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView topic,description;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.newTitle);
            description = itemView.findViewById(R.id.newsDescription);
        }
    }
}
