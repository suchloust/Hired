package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CompanyRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);
    }

    public void register(View v){
        EditText companyEmail = findViewById(R.id.companyEmail);
        EditText companyPswd = findViewById(R.id.companyPassword);
        EditText confirmPswd = findViewById(R.id.companyConfirmPassword);

        String email = companyEmail.getText().toString();
        String pswd = companyPswd.getText().toString();
        String confirm = confirmPswd.getText().toString();




    }
}