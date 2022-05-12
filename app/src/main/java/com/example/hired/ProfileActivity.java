package com.example.hired;

import static com.example.hired.userSurvey.getPrefs;
import static com.example.hired.userSurvey.getPrefsInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {
    Button editFilters, videosBut, matchBut;
    TextView locationText, ageText, fieldPref, skillPref4, skillPref2, skillPref3, nameText, jobMatchText1;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        nameText.setText(getPrefs(this, "nameLabel"));
        locationText.setText(getPrefs(this, "addy"));
        ageText.setText(getPrefs(this, "ageLabel"));

        int fieldPos = getPrefsInt(this,"fieldPrefPosition");
            if(fieldPos==0){fieldPref.setText("None");}
            else if (fieldPos==1){fieldPref.setText("Food Service");}
            else if (fieldPos==2){fieldPref.setText("Hospitality");}
            else if(fieldPos==3){fieldPref.setText("Retail/Sales");}
            else if (fieldPos==4){fieldPref.setText("Public Health/Medical");}
            else if (fieldPos==5){fieldPref.setText("Other");}

        int expPos = getPrefsInt(this,"ExpPrefPosition");
            if (expPos==0){skillPref2.setText("None");}
            else if (fieldPos==1){skillPref2.setText("No prior experience");}
            else if (fieldPos==2){skillPref2.setText("Have held a non-paying internship/job");}
            else if(fieldPos==3){skillPref2.setText("Have held a paying job");}
            else if (fieldPos==4){skillPref2.setText("Have held a paying job within the target industry");}

        skillPref3.setText(getPrefs(this, "cert1Label"));
        skillPref4.setText(getPrefs(this,"cert2Label"));

      //  Places.Initialize(getApplicationContext(), "");
        videosBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VideoDisplay.class);
                startActivity(intent);
            }});

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
                User usy = new User(Integer.parseInt(ageText.getText().toString()), locationText.getText().toString(),  skillPref2.getText().toString(), fieldPref.getText().toString()) ;
                Location companyLoc = new Location ("456 Sesame Street", "Worcester", "MA", "01234");
              //Company comper = new  Company("Market Basket", "marketBasket@gmail.com", "12",  companyLoc, "Have held a paying job", "Retail/Sales");
                //double compatScore1 = usy.matchWithCompany(comper);
               //jobMatchText1.setText(comper.getName() + " ~" + comper.getLocation().getCity() +", " + comper.getLocation().getState() + ": " + String.valueOf(compatScore1));

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

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}


