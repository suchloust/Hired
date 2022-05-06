package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanySurveyPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_survey_page);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
    }

    //storing the Company input in the Firebase for every field
    public void performSave(View v){
        Log.d("CompanySurveyPage", "In the performSave method");

        Company company = new Company();

        EditText companyNameRaw = findViewById(R.id.companyNameField);
            String companyName = companyNameRaw.getText().toString();
            company.setName(companyName);
        Log.d("CompanySurveyPage", companyName);


         EditText companyEmailRaw = findViewById(R.id.companyEmailField);
            String companyEmail = companyEmailRaw.getText().toString();
            company.setEmail(companyEmail);
        Log.d("CompanySurveyPage", companyEmail);

        EditText companyURLRaw = findViewById(R.id.companyURLField);
        String companyUrl = companyNameRaw.getText().toString();
        company.setUrl(companyUrl);

           EditText minAgeRaw = findViewById(R.id.ageMinimum);
            String ageMin = minAgeRaw.getText().toString();
            company.setAgeMinimum(ageMin);
        Log.d("CompanySurveyPage", ageMin);

            EditText companyStreetRaw = findViewById(R.id.companyStreetAddress);
            String companyStreet = companyStreetRaw.getText().toString();

            EditText companyCityRaw = findViewById(R.id.companyCity);
            String companyCity = companyCityRaw.getText().toString();

            EditText companyStateRaw = findViewById(R.id.companyState);
            String companyState = companyStateRaw.getText().toString();

            EditText zipCodeRaw = findViewById(R.id.zipCodeField);
            String zipCode = zipCodeRaw.getText().toString();
            Location l = new Location(companyStreet, companyCity, companyState, zipCode);
            company.setLocation(l);

        Log.d("CompanySurveyPage", companyStreet + companyCity + companyState + zipCode);

       Spinner experienceReqRaw = findViewById(R.id.experienceLevels);
            String experienceReq = experienceReqRaw.getSelectedItem().toString();
            company.setExperienceReq(experienceReq);
        Log.d("CompanySurveyPage", experienceReq);

         Spinner occupationTypeRaw = findViewById(R.id.occupationTypes);
            String occupationType = occupationTypeRaw.getSelectedItem().toString();
            company.setCompanyType(occupationType);
        Log.d("CompanySurveyPage", occupationType);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            DatabaseReference child = myRef.child(userID);
            child.push().setValue(company);

            Log.d("CompanySurveyPage", "Exited the method.");

        Toast.makeText(getApplicationContext(),"Changes saved",Toast.LENGTH_SHORT).show();
        //going to the Company profile page
        //Intent intent = new Intent(this, CompanyProfile.class);
        //startActivity(intent);

        //read this for next steps...in the profile class, we need an event listener that is listening for changes to the firebase
        //merge with Diego and implement this
        //after setting the user to the node, start displaying the Company information on their profile.
    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signOut(View v){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}