package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanySurvey extends AppCompatActivity {

    private Question surveyQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_survey);

        final Controller aController = (Controller) getApplicationContext();

        //displays the question from the ArrayList of questions in the questionView text box on the screen.
        surveyQuestion = aController.getQuestion();
       //TextView companyTextView = findViewById(R.id.questionView);
        //companyTextView.setText(surveyQuestion.getQuestion());
    }

    // Displaying the next question and storing the Company input in the database
    /*public void performCompanyStorage(View v){
        final Controller aController = (Controller) getApplicationContext();

        surveyQuestion = aController.getQuestion();
        TextView companyTextView = findViewById(R.id.questionView);
        companyTextView.setText(surveyQuestion.getQuestion());

        Log.d("CompanySurvey", "In the performCompanyStorage method");

        EditText companyResponse = findViewById(R.id.answerField);
        String answer = companyResponse.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference childRef = myRef.child("Question" + " " + aController.getQuestionNumber());

        childRef.push().setValue(answer);
    }*/

    /*public void performNext(View v){
        final Controller aController = (Controller) getApplicationContext();

        surveyQuestion = aController.getQuestion();
        TextView companyTextView = findViewById(R.id.questionView);
        companyTextView.setText(surveyQuestion.getQuestion());

        //Resetting button back to gray
        //Button answerButton = findViewById(R.id.answerButton);
        //answerButton.setBackgroundColor(Color.rgb(221,160,221));
        //answerButton.setText("Check answer");
    }

    public void goToProfile(View v){
        Intent intent = new Intent(this, CompanyProfile.class);
        startActivity(intent);
    }*/

    }