package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                Intent intent = new Intent(getApplicationContext(), MultimediaPlayer.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationText.setText(getPrefs(this));
    }

}