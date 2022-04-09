package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class userSurvey extends AppCompatActivity {

    EditText userLocation;
    TextView locationLabel;
    Button saveSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_survey);

        userLocation = (EditText) findViewById(R.id.userLocationInput);
        locationLabel = (TextView) findViewById(R.id.locationLabel);
        saveSurvey = (Button) findViewById(R.id.saveButton);
        getPrefs(this);
        saveSurvey.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick (View view){
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
        }
    });
}

    @Override
    protected void onResume() {
        super.onResume();
        userLocation.setText(getPrefs(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
    }

    public static void setPrefs(Context context, String pref, String key) {
        SharedPreferences preferences = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, pref);
        editor.commit();
    }

    public static String getPrefs(Context context) {
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString("locationLabel", "");
        return s1;
    }


}
