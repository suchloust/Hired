package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;
import static com.example.hired.userSurvey.getPrefsInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that displays user information on the User profile page. The data is stored locally and redisplayed using Shared Preferences.
 */

public class ProfileActivity extends AppCompatActivity implements Serializable {

    private DatabaseReference ref;
    private ArrayList companies;
    private Button videosBut, matchBut;
    private TextView locationText, ageText, fieldPref, skillPref4, skillPref2, skillPref3, nameText, jobMatchText1,jobMatchText2,jobMatchText3,jobMatchText4,jobMatchText5;
    private Button editFilters;

    /**
     * Setting the content view to activity_profile.xml. Initialized all the activity_main TextViews and Buttons.
     * Initializes the FirebaseDatabase reference, ref. Made a ValueOnClickListener to get a DataSnapshot of the Realtime Database.
     * Adds all of the companies objected stored in the Realtime Database to an ArrayList<Company> companies.
     * Displays all of the user inputted data into the TextViews using Shared Preferences.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        companies = new ArrayList<Company>();

        editFilters = (Button) findViewById(R.id.editFiltersButton);
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
        jobMatchText2 = (TextView) findViewById(R.id.jobMatch2);
        jobMatchText3 = (TextView) findViewById(R.id.jobMatch3);
        jobMatchText4 = (TextView) findViewById(R.id.jobMatch4);
        jobMatchText5 = (TextView) findViewById(R.id.jobMatch5);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap1 : snapshot.getChildren()) {
                    for(DataSnapshot snap : snap1.getChildren()){
                        Company comp = snap.getValue(Company.class);
                        companies.add(comp);
                        Log.d("ProfileActivity", "Company url:" + comp.getUrl());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "addy"));
        ageText.setText(getPrefs(this, "ageLabel"));

        int fieldPos = getPrefsInt(this,"fieldPrefPosition");
        if(fieldPos==0){fieldPref.setText("None");}
        else if (fieldPos==1){fieldPref.setText("Food Service");}
        else if (fieldPos==2){fieldPref.setText("Hospitality");}
        else if(fieldPos==3){fieldPref.setText("Retail/Sales");}
        else if (fieldPos==4){fieldPref.setText("Public Health");}
        else if (fieldPos==5){fieldPref.setText("Other");}

        int expPos = getPrefsInt(this,"ExpPrefPosition");
        if (expPos==0){skillPref2.setText("None");}
        else if (fieldPos==1){skillPref2.setText("No prior experience");}
        else if (fieldPos==2){skillPref2.setText("Have held a non-paying internship/job");}
        else if(fieldPos==3){skillPref2.setText("Have held a paying job");}
        else if (fieldPos==4){skillPref2.setText("Paying job within the target industry");}

        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this, "cert2Label"));


        videosBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayToVideo(companies);
            }
        });

        editFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userSurvey.class);
                startActivity(intent);
            }

        });

        matchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("poorvi method","poorvi param: " + companies);
                matchCompany(companies);
            }
        });
    }

    /**
     * Redisplays all the user data stored locally on the profile page using Shared Preferences.
     */
    @Override
    protected void onResume() {
        super.onResume();
        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "addy"));
        ageText.setText(getPrefs(this, "ageLabel"));

        int fieldPos = getPrefsInt(this,"fieldPrefPosition");
        if(fieldPos==0){fieldPref.setText("None");}
        else if (fieldPos==1){fieldPref.setText("Food Service");}
        else if (fieldPos==2){fieldPref.setText("Hospitality");}
        else if(fieldPos==3){fieldPref.setText("Retail/Sales");}
        else if (fieldPos==4){fieldPref.setText("Public Health");}
        else if (fieldPos==5){fieldPref.setText("Other");}

        int expPos = getPrefsInt(this,"ExpPrefPosition");
        if (expPos==0){skillPref2.setText("None");}
        else if (fieldPos==1){skillPref2.setText("No prior experience");}
        else if (fieldPos==2){skillPref2.setText("Have held a non-paying internship/job");}
        else if(fieldPos==3){skillPref2.setText("Have held a paying job");}
        else if (fieldPos==4){skillPref2.setText("Paying job within the target industry");}

        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this,"cert2Label"));
    }

    /**
     * Orders the contents of an ArrayList of Company objects by their compatibility score with the user.
     * A User object is constructed using the local data from the profile. The matchWithCompany() method is invoked to return the
     * compatibility score a User and Company object.
     * Once ordered using a Selection Sort algorithm, the top five ranked Companies are displayed to the ProfileActivity.
     * @param companies
     */
    public void matchCompany(ArrayList<Company> companies){
        User user = new User(Integer.parseInt(ageText.getText().toString()), locationText.getText().toString(),  skillPref2.getText().toString(), fieldPref.getText().toString());
        for (int i = 0; i < companies.size()-1;i++){
            int position = i;
            for(int k = i +1; k < companies.size(); k++){
                if (user.matchWithCompany(companies.get(k)) < user.matchWithCompany(companies.get(position))){
                    position = k;

                Company temp = companies.get(i);
                companies.set(i, companies.get(position));
                companies.set(position, temp);
            }}
        }

        jobMatchText1.setText(companies.get(companies.size()-1).getName() + " ~" + companies.get(companies.size()-1).getLocation().getCity() +", " + companies.get(companies.size()-1).getLocation().getState() + ": " + String.valueOf(user.matchWithCompany(companies.get(companies.size()-1)))+"%");
        jobMatchText2.setText(companies.get(companies.size()-2).getName() + " ~" + companies.get(companies.size()-2).getLocation().getCity() +", " + companies.get(companies.size()-2).getLocation().getState() + ": " + String.valueOf(user.matchWithCompany(companies.get(companies.size()-2)))+"%");
        jobMatchText3.setText(companies.get(companies.size()-3).getName() + " ~" + companies.get(companies.size()-3).getLocation().getCity() +", " + companies.get(companies.size()-3).getLocation().getState() + ": " + String.valueOf(user.matchWithCompany(companies.get(companies.size()-3)))+"%");
        jobMatchText4.setText(companies.get(companies.size()-4).getName() + " ~" + companies.get(companies.size()-4).getLocation().getCity() +", " + companies.get(companies.size()-4).getLocation().getState() + ": " + String.valueOf(user.matchWithCompany(companies.get(companies.size()-4)))+"%");
        jobMatchText5.setText(companies.get(companies.size()-5).getName() + " ~" + companies.get(companies.size()-5).getLocation().getCity() +", " + companies.get(companies.size()-5).getLocation().getState() + ": " + String.valueOf(user.matchWithCompany(companies.get(companies.size()-5)))+"%");
    }

    private void arrayToVideo(ArrayList<Company> c) {
        ArrayList<String> urls = new ArrayList<String>();
        for (int i=0; i<c.size(); i++){
            urls.add(c.get(i).getUrl());
        }

        Log.d("testing","companies urls: " + urls);
        Intent intent = new Intent(ProfileActivity.this, VideoDisplay.class);
        intent.putExtra("key", urls);
        startActivity(intent);
    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}




