package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {
    Button filtersBut, videosBut, matchBut;
    TextView locationText, ageText, fieldPref, skillPref4, skillPref2, skillPref3, nameText, jobMatchText1;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        filtersBut = (Button) findViewById(R.id.filtersButton);
        videosBut = (Button) findViewById(R.id.videos_button);
        matchBut = (Button) findViewById(R.id.matchButton);
        locationText = (TextView) findViewById(R.id.locationLabel);
        fieldPref = (TextView) findViewById(R.id.filter1);
        nameText = (TextView) findViewById(R.id.userName);
        skillPref4 = (TextView) findViewById(R.id.filter4);
        skillPref2 = (TextView) findViewById(R.id.filter2);
        skillPref3 = (TextView) findViewById(R.id.filter3);
        ageText = (TextView) findViewById(R.id.ageLabel);
        jobMatchText1 = (TextView) findViewById(R.id.jobMatch1);
        logo = (ImageView) findViewById(R.id.logoHomeButton);

        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "locationLabel"));
        ageText.setText(getPrefs(this, "ageLabel"));
        fieldPref.setText(getPrefs(this, "interestLabel"));
        skillPref2.setText(getPrefs(this, "skill1Label"));
        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this,"cert2Label"));

      //  Places.Initialize(getApplicationContext(), "");

        logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        videosBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VideoDisplay.class);
                startActivity(intent);
            }});

        filtersBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userSurvey.class);
                startActivity(intent);
            }

        });

        matchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User usy = new User(Integer.parseInt(ageText.getText().toString()), userSurvey.getAddress(),  skillPref2.getText().toString(), fieldPref.getText().toString()) ;
               Company comper = new  Company("Market Basket", "marketBasket@gmail.com",  "14" , "main st", "worcester", "massachusetts", "Have held a paying job", "Retail/Sales");
                double compatScore1 = usy.matchWithCompany(comper);
                jobMatchText1.setText(comper.getName() + ": " + String.valueOf(compatScore1));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "locationLabel"));
        ageText.setText(getPrefs(this, "ageLabel"));
        fieldPref.setText(getPrefs(this, "interestLabel"));
        skillPref2.setText(getPrefs(this, "skill1Label"));
        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this,"cert2Label"));
    }

}


