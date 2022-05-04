package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateJobsActivity extends AppCompatActivity {
    TextInputLayout title, salary, companyName, location, contactNo, email;
    Button updateBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jobs);

        title = findViewById(R.id.jobTitle_updateJobs);
        salary = findViewById(R.id.salary_updateJobs);
        companyName = findViewById(R.id.company_updateJobs);
        location = findViewById(R.id.location_updateJobs);
        contactNo = findViewById(R.id.mobile_updateJobs);
        email = findViewById(R.id.email_updateJobs);
        updateBtn = findViewById(R.id.update_btn_jobs);

    }
}