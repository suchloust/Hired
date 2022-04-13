package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;
import static com.example.hired.userSurvey.setPrefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    Button filtersBut;
    TextView locationText, ageText, fieldPref, skillPref1, skillPref2, skillPref3, skillPref4;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    Button filtersBut;
    Button videosBut;
    TextView locationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        filtersBut = (Button) findViewById(R.id.filtersButton);
        locationText = (TextView) findViewById(R.id.locationLabel);
        fieldPref = (TextView) findViewById(R.id.filter1);
        skillPref1 = (TextView) findViewById(R.id.filter);
        skillPref2 = (TextView) findViewById(R.id.filter2);
        skillPref3 = (TextView) findViewById(R.id.filter3);
        ageText = (TextView) findViewById(R.id.ageLabel);
        locationText.setText(getPrefs(this, "locationLabel"));
        ageText.setText(getPrefs(this, "ageLabel"));

        skillPref1.setText(getPrefs(this, "skill1Label"));
        skillPref2.setText(getPrefs(this, "skill2Label"));
        skillPref3.setText(getPrefs(this, "skill3Label"));

        videosBut = (Button) findViewById(R.id.videos_button);
        locationText = (TextView) findViewById(R.id.locationLabel);
        locationText.setText(getPrefs(this));
        filtersBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userSurvey.class);
                startActivity(intent);
            }

        });
        videosBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VideoDisplay.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationText.setText(getPrefs(this, "locationLabel"));
        ageText.setText(getPrefs(this, "ageLabel"));
        fieldPref.setText(getPrefs(this, "interestLabel"));
        skillPref1.setText(getPrefs(this, "skill1Label"));
        skillPref2.setText(getPrefs(this, "skill2Label"));
        skillPref3.setText(getPrefs(this, "skill3Label"));
        locationText.setText(getPrefs(this));
    }

}