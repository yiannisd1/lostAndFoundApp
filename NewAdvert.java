package com.example.lostandfoundappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfoundappfinal.data.DatabaseHelper;
import com.example.lostandfoundappfinal.model.LostAndFound;

public class NewAdvert extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;


    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);


        RadioButton radio_lost = findViewById(R.id.radio_lost);
        RadioButton radio_found = findViewById(R.id.radio_found);
        EditText textName = findViewById(R.id.textName);
        EditText textPhone = findViewById(R.id.textPhone);
        EditText textDescription = findViewById(R.id.textDescription);
        EditText textDate = findViewById(R.id.textDate);
        EditText textLocation = findViewById(R.id.textLocation);


        db = new DatabaseHelper(this);

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.post_type_selected);


        Button buttonSave = findViewById(R.id.saveButton);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int postTypeId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(postTypeId);

                String typeOfAdvert;
                if (radio_lost.isChecked()){
                    typeOfAdvert = "lost";
                }
                else
                {
                    typeOfAdvert = "found";
                }

                String name = textName.getText().toString();
                String phone = textPhone.getText().toString();
                String description = textDescription.getText().toString();
                String date = textDate.getText().toString();
                String location = textLocation.getText().toString();

                LostAndFound lostAndFound = new LostAndFound(typeOfAdvert, name, phone, description, date, location);

                boolean addData = db.insertLostAndFound(lostAndFound);

                if (addData)
                {

                    Toast.makeText(NewAdvert.this, "Successful Entry Log", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(NewAdvert.this, "Unsuccessful Entry Log", Toast.LENGTH_SHORT).show();
                }

                Intent saveIntent = new Intent( NewAdvert.this, LostAndFoundItems.class);
                startActivity(saveIntent);

            }

        });
    }

    public void checkPostType(View v) {
        int postTypeId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(postTypeId);

        Toast.makeText(this, "Post Type Selected:" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }




}