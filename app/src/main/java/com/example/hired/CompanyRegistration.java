package com.example.hired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CompanyRegistration extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);
        auth= FirebaseAuth.getInstance();
    }

    public void register(View v){
        EditText companyEmail = findViewById(R.id.companyEmail);
        EditText companyPassword = findViewById(R.id.companyPassword);
        EditText confirmPassword = findViewById(R.id.companyConfirmPassword);

        String email = companyEmail.getText().toString();
        String password = companyPassword.getText().toString();
        String confirm = confirmPassword.getText().toString();
        Log.d("CompanySurveyPage", email + " " + password + " " + confirm);

        if (!confirmPassword(password, confirm)){
            Toast.makeText(CompanyRegistration.this, "Password does not match username.",Toast.LENGTH_SHORT);
            Log.d("CompanySurveyPage", "not confirmed" + " " + password + " " + confirm);
        }
        else{
            Log.d("CompanySurveyPage", "I'm here, about to authenticate");
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(CompanyRegistration.this, "New user created for" + email + ".",Toast.LENGTH_SHORT);
                      Log.d("CompanySurveyPage", "Successful authentication.");
                      performCompanySurvey(v);
                  }
                  else{
                      Log.d("CompanySurveyPage", "Failed to create new user. Try again.");
                      Log.d("CompanySurveyPage", " " + task.getException());
                      Toast.makeText(CompanyRegistration.this, "Failed to create new user. Try again.",Toast.LENGTH_SHORT);
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
            //Make sure to link this to the Company Profile page after we merge!!
            Intent intent = new Intent(this, CompanySurveyPage.class);
            startActivity(intent);
            this.finish();
        }
    }

    private boolean confirmPassword(String password, String confirmation){
        if (password.equals(confirmation)){
            return true;
        }
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmation)){
            return false;
        }
        return false;
    }

    public void performCompanyLogin(View v){
        Intent intent = new Intent(this, CompanyLogin.class);
        startActivity(intent);
    }

    private void performCompanySurvey(View v){
        Intent intent = new Intent(this, CompanySurveyPage.class);
        startActivity(intent);
    }
}