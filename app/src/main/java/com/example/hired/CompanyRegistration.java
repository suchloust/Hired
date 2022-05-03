package com.example.hired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CompanyRegistration extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);
    }

    public void register(View v){
        auth= FirebaseAuth.getInstance();
        EditText companyEmail = findViewById(R.id.companyEmail);
        EditText companyPswd = findViewById(R.id.companyPassword);
        EditText confirmPswd = findViewById(R.id.companyConfirmPassword);


        String email = companyEmail.getText().toString();
        String pswd = companyPswd.getText().toString();
        String confirm = confirmPswd.getText().toString();

        if (!confirmPassword(pswd, confirm)){
            Toast.makeText(CompanyRegistration.this, "Password does not match username.",Toast.LENGTH_SHORT);
        }
        else{
            auth.createUserWithEmailAndPassword(email,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(CompanyRegistration.this, "New user created for" + email + ".",Toast.LENGTH_SHORT);
                      //take to profile page?
                  }
                  else{
                      Toast.makeText(CompanyRegistration.this, "Fail to create new user. Try again.",Toast.LENGTH_SHORT);
                  }
                }
            });
        }
        performCompanySurvey(v);
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