package com.example.hired;

import android.app.Application;

import java.util.ArrayList;
import java.util.Random;

public class Controller extends android.app.Application{
    ArrayList<Question> compQuestions = new ArrayList<Question>();
    private static int questionNumber=0;

    public ArrayList<Question> getQuestions(){

        return compQuestions;
    }

    public void addQuestion(Question question){

        compQuestions.add(question);
    }

    public Question getQuestion(){
        int qNum = questionNumber;
        questionNumber++;
        return compQuestions.get(qNum);
    }

    public int getQuestionNumber(){

        return questionNumber-1;
    }
}
