package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userUS, userPW, companyUS, companyPW, userRTPW;
    Button userSubButton, companySubButton,existingUSButton ;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userUS = (EditText) findViewById(R.id.userEmailAddress);
        userPW = (EditText)  findViewById(R.id.userPassword);
        userSubButton = (Button) findViewById(R.id.userSubmitButton);
        userRTPW = (EditText) findViewById(R.id.userRetypePassword);

        companyUS = (EditText) findViewById(R.id.companyEmailAddress);
        companyPW = (EditText) findViewById(R.id.companyPassword);
        companySubButton = (Button) findViewById(R.id.companySubmitButton);

        existingUSButton = (Button) findViewById(R.id.existingUserButton);
        DB = new DataBaseHelper(this);


     userSubButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            String user = userUS.getText().toString();
            String pass = userPW.getText().toString();
            String repass = userRTPW.getText().toString();

            if(user.equals("")||pass.equals("")||repass.equals("")){
                Toast.makeText(MainActivity.this,"Please enter all fields", Toast.LENGTH_SHORT).show();
            }
            else {
                if(pass.equals(repass)){
                Boolean checkUser = DB.checkUsername(user);
                Toast.makeText(MainActivity.this,"Checkuser succeeded", Toast.LENGTH_SHORT).show();
                    if(checkUser == false){
                        Boolean insert = DB.insertData(user, pass);

                        if (insert == true){
                        Toast.makeText(MainActivity.this,"Welcome to WorkSpace", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        }
                        else{
                        Toast.makeText(MainActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                }
            } }
     });

     existingUSButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
             startActivity(intent);
         }
     });


    }
}