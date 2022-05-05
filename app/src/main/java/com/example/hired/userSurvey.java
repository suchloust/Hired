package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

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

public class userSurvey extends AppCompatActivity {

    EditText userAge, userName,  userZip, cert1, cert2;
    public static EditText userState, userStreet, userLocation;

    Spinner fieldPreference, skill1;
    Button saveSurvey;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        userLocation = (EditText) findViewById(R.id.userLocationInput);
        userAge = (EditText) findViewById(R.id.userAgeInput);
        userName = (EditText) findViewById(R.id.userNameInput);
        userStreet = (EditText) findViewById(R.id.userStreetAddressInput);
        userZip = (EditText) findViewById(R.id.userZipInput);
        userState = (EditText) findViewById(R.id.userStateInput);
        saveSurvey = (Button) findViewById(R.id.saveButton);
        fieldPreference = (Spinner) findViewById(R.id.interestSpinner);
        skill1 = (Spinner) findViewById(R.id.skill1spinner);
        logo = (ImageView) findViewById(R.id.miniLogo);
        cert1 = (EditText) findViewById(R.id.cert1Input);
        cert2 = (EditText) findViewById(R.id.cert2Input);
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
            setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
            setPrefs(getApplicationContext(), getAddress(), "locationLabel");
            setPrefs(getApplicationContext(), userStreet.getText().toString(), "streetLabel");
            setPrefs(getApplicationContext(), userState.getText().toString(), "stateLabel");
            setPrefs(getApplicationContext(), userZip.getText().toString(), "zipLabel");
            setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
            setPrefs(getApplicationContext(), skill1.getSelectedItem().toString(), "skill1Label");
            setPrefs(getApplicationContext(), cert1.getText().toString(), "cert1Label");
            setPrefs(getApplicationContext(), cert2.getText().toString(), "cert2Label");
        }
    });


}

    @Override
    protected void onResume() {
        super.onResume();
        userName.setText(getPrefs(this, "nameLabel"));
        userLocation.setText(getPrefs(this, "locationLabel"));
        userAge.setText(getPrefs(this, "ageLabel"));
        userStreet.setText(getPrefs(this, "streetLabel"));
        userZip.setText(getPrefs(this, "zipLabel"));
        userState.setText(getPrefs(this, "stateLabel"));
        cert1.setText(getPrefs(this, "cert1Label"));
        cert2.setText(getPrefs(this, "cert2Label"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPrefs(getApplicationContext(), userName.getText().toString(), "nameLabel");
        setPrefs(getApplicationContext(),getAddress(), "locationLabel");
        setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
        setPrefs(getApplicationContext(), userStreet.getText().toString(), "streetLabel");
        setPrefs(getApplicationContext(), userState.getText().toString(), "stateLabel");
        setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
        setPrefs(getApplicationContext(), skill1.getSelectedItem().toString(), "skill1Label");
        setPrefs(getApplicationContext(), userZip.getText().toString(), "zipLabel");
        setPrefs(getApplicationContext(), cert1.getText().toString(), "cert1Label");
        setPrefs(getApplicationContext(), cert2.getText().toString(), "cert2Label");

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

    public static String getAddress(){
        return userStreet.getText().toString()  + ", " + userLocation.getText().toString() + ", " + userState.getText().toString();

    }


}
