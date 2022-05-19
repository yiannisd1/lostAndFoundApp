package com.example.lostandfoundappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfoundappfinal.data.DatabaseHelper;
import com.example.lostandfoundappfinal.model.LostAndFound;

import java.util.ArrayList;
import java.util.List;

public class LostAndFoundItems extends AppCompatActivity {

    ListView NamesList;
    DatabaseHelper db;
    ArrayList<String> lostAndFoundArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_items);

        NamesList = findViewById(R.id.lostAndFoundList);
        lostAndFoundArrayList = new ArrayList<>();
        db = new DatabaseHelper(LostAndFoundItems.this);

        List<LostAndFound> namesList = db.fetchAllLostAndFound();
        for (LostAndFound lostAndFound : namesList) {
            lostAndFoundArrayList.add(lostAndFound.getTypeOfAdvert() + " " + lostAndFound.getName());
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lostAndFoundArrayList);
        NamesList.setAdapter(adapter);

        NamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent passData = new Intent(LostAndFoundItems.this, DisplayInfo.class);
                passData.putExtra("typeOfAdvert", namesList.get(pos).getTypeOfAdvert());
                passData.putExtra("name", namesList.get(pos).getName());
                passData.putExtra("phone", namesList.get(pos).getPhone());
                passData.putExtra("description", namesList.get(pos).getDescription());
                passData.putExtra("date", namesList.get(pos).getDate());
                passData.putExtra("location", namesList.get(pos).getLocation());
                startActivity(passData);
            }

        });
    }
}
