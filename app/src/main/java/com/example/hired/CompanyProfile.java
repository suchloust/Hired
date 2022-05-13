package com.example.hired;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class displays the Company information of a logged in company user.
 */
public class CompanyProfile extends AppCompatActivity {
    private Button edit;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        // start *advertising for employees
        ref = FirebaseDatabase.getInstance().getReference(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snappy: snapshot.getChildren()){
                    Company company = snappy.getValue(Company.class);
                    Log.d("CompanyProfile", "In the updateCompanyTest method");

                    TextView companyNameHeader = findViewById(R.id.companyNameHeader);
                    TextView companyEmail = findViewById(R.id.companyEmailHeader);
                    TextView companyLocation = findViewById(R.id.companyLocationHeader);
                    TextView companyIndustry= findViewById(R.id.companyIndustryHeader);
                    TextView ageMin= findViewById(R.id.companyAge);
                    TextView expReq= findViewById(R.id.companyExp);
                    TextView cert= findViewById(R.id.companyCert);

                    companyNameHeader.setText(company.getName());
                    companyEmail.setText(company.getEmail());
                    companyLocation.setText(company.getLocation().toString());
                    companyIndustry.setText(company.getCompanyType());
                    ageMin.setText(company.getAgeMinimum());
                    expReq.setText(company.getExperienceReq());
                    cert.setText(company.getCertification());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit = (Button) findViewById(R.id.comp_survey_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompanySurveyPage.class);
                startActivity(intent);
            }});
    }

    public void signOut(View v){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}