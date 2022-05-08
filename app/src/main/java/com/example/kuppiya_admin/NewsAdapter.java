package com.example.kuppiya_admin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    Context context;
    ArrayList<newsHelperClass> list;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("news");

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

        newsHelperClass NewsHelperClass = list.get(position);
        holder.topic.setText(NewsHelperClass.getTopic());
        holder.description.setText(NewsHelperClass.getDescription());


        holder.updateNews.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateNewsActivity.class);
            intent.putExtra("EDIT", (Serializable) NewsHelperClass);
            context.startActivity(intent);
        });

        holder.deleteNews.setOnClickListener(view -> {
            myRef.child(NewsHelperClass.getKey()).removeValue().addOnSuccessListener(suc->
            {
                Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                notifyItemRemoved(position);
                list.remove(NewsHelperClass);
            }).addOnFailureListener(er->
            {
                Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView topic,description;
        Button updateNews, deleteNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            topic = itemView.findViewById(R.id.newsTopic);
            description = itemView.findViewById(R.id.newsDescription);
            updateNews = itemView.findViewById(R.id.update_btn_manageNews);
            deleteNews = itemView.findViewById(R.id.delete_btn_manageNews);
        }
    }
}
