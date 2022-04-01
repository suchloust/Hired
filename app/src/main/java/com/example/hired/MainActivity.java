package com.example.hired;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    CSVFileWriter csv;
    StringBuffer filePath;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveButton = findViewById(R.id.userSubmitButton);
        EditText editText = findViewById(R.id.userEmailAddress);

        filePath = new StringBuffer();
        filePath.append("/sdcard/abc.csv");
        file = new File(filePath.toString());

        csv = new CSVFileWriter(file);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                csv.writeHeader(editText.getText().toString());

            }
        });
    }
}

    class CSVFileWriter {

        private PrintWriter csvWriter;
        private File file;

        public CSVFileWriter(File file) {
            this.file = file;

        }

        public void writeHeader(String data) {

            try {
                if (data != null) {

                    csvWriter = new PrintWriter(new FileWriter(file, true));
                    csvWriter.print(",");
                    csvWriter.print(data);
                    csvWriter.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}