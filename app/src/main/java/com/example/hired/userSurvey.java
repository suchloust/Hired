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

/**
 * Class that takes in user input to fill in filters and updates each of their values using
 * Shared Preferences in both the userSurvey and User classes.
 */
public class userSurvey extends AppCompatActivity {

    EditText userAge, userName,  userZip, cert1, cert2;
    public static EditText userState, userStreet, userLocation;

    Spinner fieldPreference, skill1;
    Button saveSurvey;
    ImageView logo;

    /**
     * Initializes editText fields, spinners, and buttons.
     * Creates and OnClickListener for a saveSurvey button that saves all changes made to the compatability criteria.
     * @param savedInstanceState
     */
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
        cert1 = (EditText) findViewById(R.id.cert1Input);
        cert2 = (EditText) findViewById(R.id.cert2Input);

        saveSurvey.setOnClickListener(new View.OnClickListener(){

            /**
             * Uses Shared Preferences to store preferences with the inputted data.
             * This ensures that none of the data is erased after being editted in the userSurvey class.
             * Shared Preferences for the spinners are saved as integers which store the position of the selected item.
             * @param view
             */
        @Override
        public void onClick (View view){
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            setPrefs(getApplicationContext(), userName.getText().toString(), "nameLabel");
            setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
            setPrefs(getApplicationContext(), userLocation.getText().toString(), "locationLabel");
            setPrefs(getApplicationContext(), userStreet.getText().toString(), "streetLabel");
            setPrefs(getApplicationContext(), userState.getText().toString(), "stateLabel");
            setPrefs(getApplicationContext(), userZip.getText().toString(), "zipLabel");
            setPrefs(getApplicationContext(), getAddress(), "addy");
            setPrefsInt(getApplicationContext(), fieldPreference.getSelectedItemPosition(), "fieldPrefPosition");
            setPrefsInt(getApplicationContext(), skill1.getSelectedItemPosition(), "ExpPrefPosition");
            setPrefs(getApplicationContext(), cert1.getText().toString(), "cert1Label");
            setPrefs(getApplicationContext(), cert2.getText().toString(), "cert2Label");

        }
    });


}

    /**
     * Uses Shared Preferences to update all text fields with the inputted data.
     * This ensures that none of the data is erased after being editted in the userSurvey class.
     * Shared Preferences for the spinners are employed to set the selected item to the position of the item selected in the previous edit.
     */
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
        fieldPreference.setSelection(getPrefsInt(this,"fieldPrefPosition"), true);
        skill1.setSelection(getPrefsInt(this,"ExpPrefPosition"), true);

    }

    /**
     * Uses Shared Preferences to store preferences with the inputted data.
     * This ensures that none of the data is erased after being editted in the userSurvey class.
     * Shared Preferences for the spinners are saved as integers which store the position of the selected item.
     */
    @Override
    protected void onPause() {
        super.onPause();
        setPrefs(getApplicationContext(), userName.getText().toString(), "nameLabel");
        setPrefs(getApplicationContext(),userLocation.getText().toString(), "locationLabel");
        setPrefs(getApplicationContext(), userAge.getText().toString(), "ageLabel");
        setPrefs(getApplicationContext(), userStreet.getText().toString(), "streetLabel");
        setPrefs(getApplicationContext(), userState.getText().toString(), "stateLabel");
        setPrefs(getApplicationContext(), getAddress(), "addy");
        setPrefsInt(this, fieldPreference.getSelectedItemPosition(), "fieldPrefPosition");
        setPrefsInt(this, skill1.getSelectedItemPosition(), "ExpPrefPosition");
        setPrefs(getApplicationContext(), userZip.getText().toString(), "zipLabel");
        setPrefs(getApplicationContext(), cert1.getText().toString(), "cert1Label");
        setPrefs(getApplicationContext(), cert2.getText().toString(), "cert2Label");

    }

    /**
     * Uses Shared Preferences to store preferences with the inputted data for the spinners only.
     * Shared Preferences for the spinners are saved as integers which store the position of the selected item.
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        setPrefsInt(this, fieldPreference.getSelectedItemPosition(), "fieldPrefPosition");
        setPrefsInt(this, skill1.getSelectedItemPosition(), "ExpPrefPosition");
    }

    /**
     * method used to declare a shared preference to a specific string inputted by the user
     * @param context - context of application window
     * @param pref - the user inputted String
     * @param key - the preference's name in shared preferences
     */
    public static void setPrefs(Context context, String pref, String key) {
        SharedPreferences preferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, pref);
        editor.commit();
    }

    /**
     * method used to get the shared preference under a parameter key title.
     * @param context - context of applicatoin window
     * @param key - the preference's name in shared preferences
     * @return - String input of user
     */
    public static String getPrefs(Context context, String key) {
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String s1 = sh.getString(key, "");
        return s1;
    }

    /**
     * method used to declare a shared preference for spinners using selected item position
     * @param context - context of application window
     * @param pref - the user inputted int
     * @param key - the preference's name in shared preferences
     */
    public static void setPrefsInt(Context context, int pref, String key){
        SharedPreferences preferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, pref);
        editor.commit();
    }

    /**
     * method used to get the shared preference under a parameter key title for spinners only
     * @param context - context of applicatoin window
     * @param key - the preference's name in shared preferences
     * @return - int position of selected item
     */
    public static int getPrefsInt(Context context, String key) {
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int s1 = sh.getInt(key, MODE_PRIVATE);
        return s1;
    }

    /**
     * formats the user's address in conventional location format
     * @return - user's address
     */
    public static String getAddress(){
        return userStreet.getText().toString()  + ", " + userLocation.getText().toString() + ", " + userState.getText().toString();

    }


}
