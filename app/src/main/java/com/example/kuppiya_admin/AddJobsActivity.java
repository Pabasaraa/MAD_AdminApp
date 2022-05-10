package com.example.kuppiya_admin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddJobsActivity extends AppCompatActivity {
    TextInputLayout title, salary, companyName, location, contactNo, email;
    Button addBtn;
    ImageView backImg;
    public String CHANNEL_ID = "Id_1";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("jobs");
    DatabaseReference keyRef = myRef.push();

//
//    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("New job added!")
//            .setStyle(new NotificationCompat.BigTextStyle()
//                    .bigText("The details of the new job successfully added to the database."))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jobs);

        CharSequence name = "Add Job notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription("Notification that shows when a job added to the DB");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        title = findViewById(R.id.jobTitle_addJobs);
        salary = findViewById(R.id.salary_addJobs);
        companyName = findViewById(R.id.company_addJobs);
        location = findViewById(R.id.location_addJobs);
        contactNo = findViewById(R.id.mobile_addJobs);
        email = findViewById(R.id.email_addJobs);
        addBtn = findViewById(R.id.add_btn_jobs);
        backImg = findViewById(R.id.back_button_addJobs);


        addBtn.setOnClickListener(new View.OnClickListener() {
            private String Tag = "Tag//";

            @Override
            public void onClick(View view) {

                String jobTitle = Objects.requireNonNull(title.getEditText()).getText().toString();
                String  jobSalary = Objects.requireNonNull(salary.getEditText()).getText().toString();
                String company = Objects.requireNonNull(companyName.getEditText()).getText().toString();
                String jobLocation = Objects.requireNonNull(location.getEditText()).getText().toString();
                String mobile = Objects.requireNonNull(contactNo.getEditText()).getText().toString();
                String jobEmail = Objects.requireNonNull(email.getEditText()).getText().toString();
                String key = keyRef.getKey();

                jobsHelperClass helperClass = new jobsHelperClass(jobTitle, jobSalary, company, jobLocation, mobile, jobEmail, key);

                keyRef.setValue(helperClass).addOnSuccessListener(suc ->
                {
                    Intent intentNotify = new Intent(getApplicationContext (), ManageJobsActivity.class);
                    intentNotify.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext (), 0, intentNotify, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext (), CHANNEL_ID)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Job Added!")
                            .setContentText("Job details added to the database successfully.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext ());
                    notificationManager.notify(0, builder.build());

                    helperClass.setKey(key);
                    Log.d(Tag, ""+key);
                    Toast.makeText(getApplicationContext(), "Record is inserted", Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent (getApplicationContext(), Home.class);
            startActivity(intent);
            finish ();
        });
    }
}