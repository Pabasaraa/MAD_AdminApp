package com.example.kuppiya_admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddJobsActivity extends AppCompatActivity {
    TextInputLayout title, salary, companyName, location, contactNo, email;
    Button addBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("jobs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jobs);

        title = findViewById(R.id.jobTitle_addJobs);
        salary = findViewById(R.id.salary_addJobs);
        companyName = findViewById(R.id.company_addJobs);
        location = findViewById(R.id.location_addJobs);
        contactNo = findViewById(R.id.mobile_addJobs);
        email = findViewById(R.id.email_addJobs);
        addBtn = findViewById(R.id.add_btn_jobs);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jobTitle = Objects.requireNonNull(title.getEditText()).getText().toString();
                String  jobSalary = Objects.requireNonNull(salary.getEditText()).getText().toString();
                String company = Objects.requireNonNull(companyName.getEditText()).getText().toString();
                String jobLocation = Objects.requireNonNull(location.getEditText()).getText().toString();
                String mobile = Objects.requireNonNull(contactNo.getEditText()).getText().toString();
                String jobEmail = Objects.requireNonNull(email.getEditText()).getText().toString();

                jobsHelperClass helperClass = new jobsHelperClass(jobTitle, jobSalary, company, jobLocation, mobile, jobEmail);

                myRef.push().setValue(helperClass);

                Context context = getApplicationContext();
                CharSequence text = "Job added!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), ManageJobsActivity.class);
                startActivity(intent);
            }
        });
    }
}