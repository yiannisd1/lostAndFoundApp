package com.example.lostandfoundappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button newAdvert;
    private Button lostAndFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newAdvert = findViewById(R.id.newAdvert);
        newAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAdvert();
            }
        });

        lostAndFound = findViewById(R.id.lostAndFoundButton);
        lostAndFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLostAndFound();
            }
        });
    }

    public void createNewAdvert() {
        Intent intent = new Intent(this, NewAdvert.class);
        startActivity(intent);
    }

    public void showLostAndFound() {
        Intent intent = new Intent(this, LostAndFoundItems.class);
        startActivity(intent);
    }
}