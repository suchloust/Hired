package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button userSubButton;
        userSubButton = (Button) findViewById(R.id.userSubmitButton);


     userSubButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Toast.makeText(MainActivity.this, "Welcome to WorkSpace", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), activity_profile.class);
             startActivity(intent);
         }

     });
    }
}