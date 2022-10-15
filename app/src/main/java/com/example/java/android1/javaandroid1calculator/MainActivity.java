package com.example.java.android1.javaandroid1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onShowFirstName();
        switchActivity();
    }

    private void switchActivity() {
        Button formActivity = findViewById(R.id.form_activity);
        formActivity.setOnClickListener(click -> {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        });
    }

    private void onShowFirstName() {
        Button showName = findViewById(R.id.show_name);
        EditText inputFirstName = findViewById(R.id.name);
        TextView firstName = findViewById(R.id.first_name);
        showName.setOnClickListener(click -> {
            if (!String.valueOf(inputFirstName.getText()).trim().equals("")) {
                firstName.setText(inputFirstName.getText());
            } else {
                firstName.setText("Please, enter your name");
            }
        });
    }
}