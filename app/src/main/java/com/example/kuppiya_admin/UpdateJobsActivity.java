package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UpdateJobsActivity extends AppCompatActivity {
    TextInputLayout title, salary, companyName, location, contactNo, email;
    Button updateBtn;
    ImageView backImg;
    public String CHANNEL_ID = "Id_1";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("jobs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jobs);

        jobsHelperClass edit_jobs = (jobsHelperClass) getIntent().getSerializableExtra("EDIT");

        CharSequence name = "Add Job notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription("Notification that shows when a job added to the DB");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        title = findViewById(R.id.jobTitle_updateJobs);
        salary = findViewById(R.id.salary_updateJobs);
        companyName = findViewById(R.id.company_updateJobs);
        location = findViewById(R.id.location_updateJobs);
        contactNo = findViewById(R.id.mobile_updateJobs);
        email = findViewById(R.id.email_updateJobs);
        updateBtn = findViewById(R.id.update_btn_jobs);
        backImg = findViewById(R.id.back_button_updateJobs);

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

                myRef.child(edit_jobs.getKey()).updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Intent intentNotify = new Intent(getApplicationContext (), ManageJobsActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext (), 0, intentNotify, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext (), CHANNEL_ID)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Job Updated!")
                            .setContentText("Job details Updated in the database successfully.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext ());
                    notificationManager.notify(0, builder.build());

                    Toast.makeText(getApplicationContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    finish();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        backImg.setOnClickListener( view -> {
            Intent intent = new Intent (getApplicationContext(), ManageJobsActivity.class);
            startActivity(intent);
            finish ();
        });
    }
}