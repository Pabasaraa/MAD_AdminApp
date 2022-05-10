package com.example.kuppiya_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageJobsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    JobsAdapter jobsAdapter;
    ArrayList<jobsHelperClass> list;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_jobs);

        recyclerView = findViewById(R.id.recyclerView11);
        backImg = findViewById(R.id.back_button_manageJobs);

        databaseReference = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("jobs");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        jobsAdapter = new JobsAdapter(this, list);
        recyclerView.setAdapter(jobsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    jobsHelperClass helperClass = dataSnapshot.getValue(jobsHelperClass.class);
                    list.add(helperClass);
                }
                jobsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backImg.setOnClickListener( view -> {
            Intent intent = new Intent (getApplicationContext(), Home.class);
            startActivity(intent);
            finish ();
        });
    }
}