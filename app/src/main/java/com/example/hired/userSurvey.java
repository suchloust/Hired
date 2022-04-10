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
import android.widget.Spinner;
import android.widget.TextView;

public class userSurvey extends AppCompatActivity {

    EditText userLocation, userAge;
    TextView locationLabel;
    Spinner fieldPreference;
    Button saveSurvey, skill1, skill2, skill3, skill4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        userLocation = (EditText) findViewById(R.id.userLocationInput);
        userAge = (EditText) findViewById(R.id.userAgeInput);
        locationLabel = (TextView) findViewById(R.id.locationLabel);
        saveSurvey = (Button) findViewById(R.id.saveButton);
        fieldPreference = (Spinner) findViewById(R.id.interestSpinner);
        skill1 = (Button) findViewById(R.id.s1Check);
        skill2 = (Button) findViewById(R.id.s2Check);
        skill3 = (Button) findViewById(R.id.s3Check);
        skill4 = (Button) findViewById(R.id.s4Check);
        saveSurvey.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick (View view){
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
            setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
            setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
            setPrefs(getApplicationContext(), skill1.getText().toString(), "skill1Label");
            setPrefs(getApplicationContext(), skill2.getText().toString(), "skill2Label");
            setPrefs(getApplicationContext(), skill3.getText().toString(), "skill3Label");
            setPrefs(getApplicationContext(), skill4.getText().toString(), "skill4Label");
        }
    });
}

    @Override
    protected void onResume() {
        super.onResume();
        userLocation.setText(getPrefs(this, "locationLabel"));
        userAge.setText(getPrefs(this, "ageLabel"));
        //skill1.setText(getPrefs(this, "skill1Label");

        //int spinPosition =  fieldPreference.getSelectedItemPosition();
        //fieldPreference.setSelection(spinPosition);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
        setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
        setPrefs(getApplicationContext(), fieldPreference.getSelectedItem().toString(), "interestLabel");
        setPrefs(getApplicationContext(), skill1.getText().toString(), "skill1Label");
        setPrefs(getApplicationContext(), skill2.getText().toString(), "skill2Label");
        setPrefs(getApplicationContext(), skill3.getText().toString(), "skill3Label");
        setPrefs(getApplicationContext(), skill4.getText().toString(), "skill4Label");

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
