package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CompanySurvey extends AppCompatActivity {

    private Question surveyQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_survey);

        final Controller aController = (Controller) getApplicationContext();

        //displays the question from the ArrayList of questions in the questionView text box on the screen.
        surveyQuestion = aController.getQuestion();
        TextView companyTextView = findViewById(R.id.questionView);
        companyTextView.setText(surveyQuestion.getQuestion());
    }
}