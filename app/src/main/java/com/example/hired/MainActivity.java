package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button userSubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSubButton = (Button) findViewById(R.id.userSubmitButton);

        userSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inty = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(inty);
            }
        });
    }
}
