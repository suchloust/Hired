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

/**
 * A class containing the logic for companies users to log into their WorkSpace accounts, which are stored using Firebase Authentication.
 */
public class CompanyLogin extends AppCompatActivity {
    private FirebaseAuth auth;

    /**
     * Setting the content view for the activity_company_login.xml page, initializing the instance of the Firebase Auth.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        auth= FirebaseAuth.getInstance();
    }

    /**
     * Sign-in method for existing companies. The method signs users in by taking in their inputted email and password from the
     * activity_company_login.xml page and calling the signInWithEmailAndPassword method on the instance of the Firebase Auth.
     * @param v
     */
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
                        performCompanyProfile(v);
                    }
                    else{
                        Toast.makeText(CompanyLogin.this, "Failed to login. Try again.",Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }

    /**
     * When the user is signed in and is not null, start the CompanyProfile page.
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user!=null){
            Intent intent = new Intent(this, CompanyProfile.class);
            startActivity(intent);
            this.finish();
        }
    }

    /**
     * When the "Are you new here? Sign up here" button is clicked, this method is called and starts the CompanyRegistration.
     * @param v
     */
    public void performCompanyRegistration(View v){
        Intent intent = new Intent(this, CompanyRegistration.class);
        startActivity(intent);
    }

    /**
     * Starts the CompanyProfile activity when called.
     * @param v
     */
    private void performCompanyProfile(View v){
        Intent intent = new Intent(this, CompanyProfile.class);
        startActivity(intent);
    }
}