package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button userSubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSubButton = (Button) findViewById(R.id.userSubmitButton);
        userSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(inty);
            }
        });

    public void performCompanySurvey(View v){
        Intent intent = new Intent(this, CompanySurveyPage.class);
        startActivity(intent);
    }

    public void performCompanyProfile(View v){
        Intent intent = new Intent(this, CompanyProfile.class);
        startActivity(intent);
    }
}