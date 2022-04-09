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

public class ProfileActivity extends AppCompatActivity {
    Button filtersBut;
    TextView locationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        filtersBut = (Button) findViewById(R.id.filtersButton);
        locationText = (TextView) findViewById(R.id.locationLabel);
        locationText.setText(getPrefs(this));
        filtersBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userSurvey.class);
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