package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginButton;
    DataBaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.userEmailAddress1);
        password = (EditText)  findViewById(R.id.userPassword1);
        loginButton = (Button) findViewById(R.id.userSubmitButton1);
        DB = new DataBaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this,"Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = DB.checkUsername(user);
                    if (checkUser == true){
                        Toast.makeText(LoginActivity.this,"Welcome to WorkSpace", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}