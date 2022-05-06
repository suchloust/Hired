package com.example.hired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CompanyLogin extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        auth= FirebaseAuth.getInstance();
    }

    public void signIn(View v){
        EditText companyEmail = findViewById(R.id.companyEmail);
        EditText companyPassword = findViewById(R.id.companyPassword);

        String email = companyEmail.getText().toString();
        String password = companyPassword.getText().toString();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Toast.makeText(CompanyLogin.this, "All fields are required.",Toast.LENGTH_SHORT);
            return;
        }
        else{
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CompanyLogin.this, "Successful login for " + email + ".",Toast.LENGTH_SHORT);
                        //go to a page?
                    }
                    else{
                        Toast.makeText(CompanyLogin.this, "Failed to login. Try again.",Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null){
            //!!Important!! Right now, I have this going to the survey page, since I don't have Diego's Company Profile Page
            //Make sure to link this to the Company Profile page after we merged!!
            Intent intent = new Intent(this, CompanySurveyPage.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void performCompanyRegistration(View v){
        Intent intent = new Intent(this, CompanyRegistration.class);
        startActivity(intent);
    }
}