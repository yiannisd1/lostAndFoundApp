package com.example.lostandfoundappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfoundappfinal.data.DatabaseHelper;

public class DisplayInfo extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String typeOfAdvert = intent.getStringExtra("typeOfAdvert");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");

        String info = "Type: " + typeOfAdvert + "\n" + "Item Name: " + name + "\n" + "Phone Number: " + phone + "\n"
                + "Description: " + description + "\n" + "Date: " + date + "\n" + "Location: " + location;

        textView.setText(info);
        Button delete = findViewById(R.id.removeButton);
        DatabaseHelper db = new DatabaseHelper(DisplayInfo.this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(typeOfAdvert, name, phone, description, date, location);
                textView.setText("");
            }
        });
    }
}