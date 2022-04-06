package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class userSurvey extends AppCompatActivity {

    EditText userLocation;
    TextView locationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        userLocation = (EditText) findViewById(R.id.userLocationInput);
        locationLabel = (TextView) findViewById(R.id.locationLabel);
    }
        @Override
        protected void onResume() {
            super.onResume();

            // Fetching the stored data
            // from the SharedPreference
            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

            String s1 = sh.getString("userLocation", "");

            locationLabel.setText(s1);
        }
        @Override
        protected void onPause() {
            super.onPause();

            // Creating a shared pref object
            // with a file name "MySharedPref"
            // in private mode
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("userLocation", userLocation.getText().toString());
            myEdit.apply();
        }

    }


