package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
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
    private Button userSubButton;
    private FirebaseAuth auth;

    /**
     * Setting the content view to the activity_main.xml page. Initializing the FirebaseAuth auth.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        /**
         * an onClickListener for the userSubmitButton to start the ProfileActivity.
         */
        userSubButton = (Button) findViewById(R.id.userSubmitButton);
        userSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(inty);
            }
        });
    }

    /**
     * When the Company "Get Started" button is clicked, the CompanyRegistration activity is started.
     * @param v
     */
    public void performCompanyRegistration(View v){
        Intent intent = new Intent(this, CompanyRegistration.class);
        startActivity(intent);
    }

    /**
     * When the "Already a user? Sign in here." button is clicked, the CompanyLogin activity is started.
     * @param v
     */
    public void performCompanyLogin(View v){
        Intent intent = new Intent(this, CompanyLogin.class);
        startActivity(intent);
    }

    /**
     * When the log out button is clicked, the current user is signed out and the MainActivity activity is started again.
     * @param v
     */
    public void signOut(View v){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}