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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



     userSubButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(MainActivity.this, "Welcome to WorkSpace", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
             startActivity(intent);
         }

     });
    }
}