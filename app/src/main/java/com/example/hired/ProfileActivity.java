package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;
import static com.example.hired.userSurvey.getPrefsInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfileActivity extends AppCompatActivity {
    private DatabaseReference ref;
    private ArrayList companies;
    private Button filtersBut, videosBut, matchBut;
    private TextView locationText, ageText, fieldPref, skillPref4, skillPref2, skillPref3, nameText, jobMatchText1,jobMatchText2,jobMatchText3,jobMatchText4,jobMatchText5;
    private ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        companies = new ArrayList<Company>();
        filtersBut = (Button) findViewById(R.id.filtersButton);
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
        logo = (ImageView) findViewById(R.id.logoHomeButton);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Company comp = snap.getValue(Company.class);
                    companies.add(comp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "addy"));
        ageText.setText(getPrefs(this, "ageLabel"));

        int fieldPos = getPrefsInt(this, "fieldPrefPosition");
        if (fieldPos == 0) {
            fieldPref.setText("None");
        } else if (fieldPos == 1) {
            fieldPref.setText("Food Service");
        } else if (fieldPos == 2) {
            fieldPref.setText("Hospitality");
        } else if (fieldPos == 3) {
            fieldPref.setText("Retail/Sales");
        } else if (fieldPos == 4) {
            fieldPref.setText("Public Health/Medical");
        } else if (fieldPos == 5) {
            fieldPref.setText("Other");
        }

        int expPos = getPrefsInt(this, "ExpPrefPosition");
        if (expPos == 0) {
            skillPref2.setText("None");
        } else if (fieldPos == 1) {
            skillPref2.setText("No prior experience");
        } else if (fieldPos == 2) {
            skillPref2.setText("Have held a non-paying internship/job");
        } else if (fieldPos == 3) {
            skillPref2.setText("Have held a paying job");
        } else if (fieldPos == 4) {
            skillPref2.setText("Have held a paying job within the target industry");
        }

        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this, "cert2Label"));

        //  Places.Initialize(getApplicationContext(), "");

        logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

        filtersBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userSurvey.class);
                startActivity(intent);
            }

        });

        matchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchCompany(companies);
            }
        });
    }




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
    public void matchCompany(ArrayList<Company> companies){
        User usy = new User(Integer.parseInt(ageText.getText().toString()), locationText.getText().toString(),  skillPref2.getText().toString(), fieldPref.getText().toString());
        for (int i = 1; i < companies.size();i++){
            if (usy.matchWithCompany(companies.get(i))<usy.matchWithCompany(companies.get(i-1))){
                Company temp = companies.get(i);
                companies.remove(i);
                companies.add(0,temp);
            }
            jobMatchText1.setText(companies.get(0).getName() + " ~" + companies.get(0).getLocation().getCity() +", " + companies.get(0).getLocation().getState() + ": " + String.valueOf(usy.matchWithCompany(companies.get(0))));
            jobMatchText2.setText(companies.get(1).getName() + " ~" + companies.get(1).getLocation().getCity() +", " + companies.get(1).getLocation().getState() + ": " + String.valueOf(usy.matchWithCompany(companies.get(1))));
            jobMatchText3.setText(companies.get(2).getName() + " ~" + companies.get(2).getLocation().getCity() +", " + companies.get(2).getLocation().getState() + ": " + String.valueOf(usy.matchWithCompany(companies.get(2))));
            jobMatchText4.setText(companies.get(3).getName() + " ~" + companies.get(3).getLocation().getCity() +", " + companies.get(3).getLocation().getState() + ": " + String.valueOf(usy.matchWithCompany(companies.get(3))));
            jobMatchText5.setText(companies.get(4).getName() + " ~" + companies.get(4).getLocation().getCity() +", " + companies.get(4).getLocation().getState() + ": " + String.valueOf(usy.matchWithCompany(companies.get(4))));
        }

}}


