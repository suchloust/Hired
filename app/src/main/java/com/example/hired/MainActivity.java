package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("MainActivity", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });

    }
    //method to read Company questions and make them instances of Question class. Called only once.
    @Override
    protected void onStart(){
        super.onStart();

        readQuestionData();
    }

    private void readQuestionData(){
        InputStream input = getResources().openRawResource(R.raw.companyquestions);

        //allows the file to be read one line at a time:
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //Get the Global Controller Class object
        final Controller aController = (Controller) getApplicationContext();

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                //Split by comma ",". Use if there are commas separating the line.
               // String[] fields = line.split(",");

                //Log.v("MainActivity",fields[0] + " " + fields[1]);

                //adds the question from the file to the ArrayList of company Questions.
                Question compQ = new Question(line);
                aController.addQuestion(compQ);
            }
        }
        catch(IOException e){
            Log.wtf("MainActivity","Error reading data on line: " + line);

        }
    }

}