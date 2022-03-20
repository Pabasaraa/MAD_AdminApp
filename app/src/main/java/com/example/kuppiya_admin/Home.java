package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button addNews, manageNews, addJobs, manageJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addJobs = findViewById(R.id.add_jobs);
        manageJobs = findViewById(R.id.manage_jobs);
        addNews = findViewById(R.id.add_news);
        manageNews = findViewById(R.id.manage_news);

        addJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddJobsActivity.class);
                startActivity(intent);
            }
        });

        manageJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageJobsActivity.class);
                startActivity(intent);
            }
        });

        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewsActivity.class);
                startActivity(intent);
            }
        });

        manageNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageNewsActivity.class);
                startActivity(intent);
            }
        });
    }
}