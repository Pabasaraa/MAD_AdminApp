package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UpdateNewsActivity extends AppCompatActivity {

    EditText topic, description;
    Button updateNewsBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("news");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);

        newsHelperClass edit_news = (newsHelperClass) getIntent().getSerializableExtra("EDIT");

        topic = findViewById(R.id.update_news_topic);
        description = findViewById(R.id.update_news_Description);
        updateNewsBtn = findViewById(R.id.update_btn_news);



        String gg = "hh";
        Log.d(gg, ""+edit_news.getKey());

        topic.setText(edit_news.getTopic());
        description.setText(edit_news.getDescription());


        updateNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("topic", topic.getText().toString());
                hashMap.put("description", description.getText().toString());

                //Just for testing purpose I passed a key
                myRef.child(edit_news.getKey()).updateChildren(hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(getApplicationContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ManageNewsActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });



    }
}