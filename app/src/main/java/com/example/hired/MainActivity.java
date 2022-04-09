package com.example.hired;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
             Intent intent = new Intent(getApplicationContext(), MultimediaPlayer.class);
             startActivity(intent);
         }

     });
    }
}