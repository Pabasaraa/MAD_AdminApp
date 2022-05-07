package com.example.kuppiya_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddNewsActivity extends AppCompatActivity {

    EditText addTopic, addDescription;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    Button newsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        addTopic = findViewById(R.id.newsTopicAdd);
        addDescription = findViewById(R.id.newsDescriptionAdd);
        newsBtn = findViewById(R.id.update_btn_news);

        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // get value
                String newsTopic =  addTopic.getText().toString();
                String  newsDescription = addDescription.getText().toString();

                newsHelperClass helperClass = new newsHelperClass(newsTopic,newsDescription);

                rootNode = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
                reference = rootNode.getReference("news");

                reference.push().setValue(helperClass);

                Context context = getApplicationContext();
                CharSequence text = "News added!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();



            }
        });


    }
}

