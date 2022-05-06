package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UpdateJobsActivity extends AppCompatActivity {
    TextInputLayout title, salary, companyName, location, contactNo, email;
    Button updateBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("jobs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jobs);

        jobsHelperClass edit_jobs = (jobsHelperClass) getIntent().getSerializableExtra("EDIT");

        title = findViewById(R.id.jobTitle_updateJobs);
        salary = findViewById(R.id.salary_updateJobs);
        companyName = findViewById(R.id.company_updateJobs);
        location = findViewById(R.id.location_updateJobs);
        contactNo = findViewById(R.id.mobile_updateJobs);
        email = findViewById(R.id.email_updateJobs);
        updateBtn = findViewById(R.id.update_btn_jobs);

        //edit_jobs.getKey() won't get the key it's only return null needs to fix it
        String gg = "hh";
        Log.d(gg, ""+edit_jobs.getKey());

        Objects.requireNonNull(title.getEditText()).setText(edit_jobs.getTitle());
        Objects.requireNonNull(salary.getEditText()).setText(edit_jobs.getSalary());
        Objects.requireNonNull(companyName.getEditText()).setText(edit_jobs.getCompanyName());
        Objects.requireNonNull(location.getEditText()).setText(edit_jobs.getLocation());
        Objects.requireNonNull(contactNo.getEditText()).setText(edit_jobs.getContactNo());
        Objects.requireNonNull(email.getEditText()).setText(edit_jobs.getEmail());


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("companyName", companyName.getEditText().getText().toString());
                hashMap.put("contactNo", contactNo.getEditText().getText().toString());
                hashMap.put("email", email.getEditText().getText().toString());
                hashMap.put("location", location.getEditText().getText().toString());
                hashMap.put("salary", salary.getEditText().getText().toString());
                hashMap.put("title", title.getEditText().getText().toString());

                //Just for testing purpose I passed a key
                myRef.child("-N11zu1ZpUIPhQLp5dyD").updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(getApplicationContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ManageJobsActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}