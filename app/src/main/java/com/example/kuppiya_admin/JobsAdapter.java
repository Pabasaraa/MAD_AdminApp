package com.example.kuppiya_admin;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    Context context;
    ArrayList<jobsHelperClass> list;

    public JobsAdapter(Context context, ArrayList<jobsHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {
        jobsHelperClass helperClass = list.get(position);
        holder.title.setText(helperClass.getTitle());
        holder.companyName.setText(helperClass.getCompanyName());

        holder.updateJObs.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateJobsActivity.class);
            intent.putExtra("EDIT", (Serializable) helperClass);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder{
        TextView title, companyName;
        Button updateJObs, deleteJobs;

        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            companyName = itemView.findViewById(R.id.company_name);
            updateJObs = itemView.findViewById(R.id.update_btn_manageJobs);
            deleteJobs = itemView.findViewById(R.id.delete_btn_manageJobs);
        }
    }
}
