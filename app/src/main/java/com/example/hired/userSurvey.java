package com.example.hired;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class userSurvey extends AppCompatActivity {

    EditText userLocation, userAge, userName;
    TextView locationLabel;
    Spinner fieldPreference, skill1, skill2, skill3;
    Button saveSurvey;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        userLocation = (EditText) findViewById(R.id.userLocationInput);
        userAge = (EditText) findViewById(R.id.userAgeInput);
        locationLabel = (TextView) findViewById(R.id.locationLabel);
        userName = (EditText) findViewById(R.id.userNameInput);
        saveSurvey = (Button) findViewById(R.id.saveButton);
        fieldPreference = (Spinner) findViewById(R.id.interestSpinner);
        skill1 = (Spinner) findViewById(R.id.skill1spinner);
        skill2 = (Spinner) findViewById(R.id.skill2spinner);
        skill3 = (Spinner) findViewById(R.id.skill3spinner);
        logo = (ImageView) findViewById(R.id.miniLogo);
        logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intenty = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intenty);
            }
        });
        saveSurvey.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View view){
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                setPrefs(getApplicationContext(), userName.getText().toString(), "nameLabel");
                setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
                setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
                setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
                setPrefs(getApplicationContext(), skill1.getSelectedItem().toString(), "skill1Label");
                setPrefs(getApplicationContext(), skill2.getSelectedItem().toString(), "skill2Label");
                setPrefs(getApplicationContext(), skill3.getSelectedItem().toString(), "skill3Label");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        userName.setText(getPrefs(this, "nameLabel"));
        userLocation.setText(getPrefs(this, "locationLabel"));
        userAge.setText(getPrefs(this, "ageLabel"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPrefs(getApplicationContext(), userName.getText().toString(), "nameLabel");
        setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
        setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
        setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
        setPrefs(getApplicationContext(), skill1.getSelectedItem().toString(), "skill1Label");
        setPrefs(getApplicationContext(), skill2.getSelectedItem().toString(), "skill2Label");
        setPrefs(getApplicationContext(), skill3.getSelectedItem().toString(), "skill3Label");

    }

    public static void setPrefs(Context context, String pref, String key) {
        SharedPreferences preferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, pref);
        editor.commit();
    }

    public static String getPrefs(Context context, String key) {
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString(key, "");
        return s1;
    }


}