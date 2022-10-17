package com.example.java.android1.javaandroid1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onShowFirstName();
        switchActivity();
        actionsWithCheckBoxAndSwitch();
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
        EditText inputFirstName = findViewById(R.id.input_first_name);
        TextView firstName = findViewById(R.id.first_name);
        showName.setOnClickListener(click -> {
            if (!String.valueOf(inputFirstName.getText()).trim().equals("")) {
                firstName.setText(inputFirstName.getText());
            } else {
                firstName.setText("Please, enter your name");
            }
        });
    }

    private void actionsWithCheckBoxAndSwitch() {
        CheckBox checkBox = findViewById(R.id.checkbox);
        Switch showOrCloseText = findViewById(R.id.switch_turn);
        TextView textView = findViewById(R.id.hello_android_text);
        checkBox.setOnClickListener(event -> {
            if (checkBox.isChecked()) {
                checkBox.setText("Turn off");
            } else {
                checkBox.setText("Turn on");
            }
        });
        showOrCloseText.setOnClickListener(event -> {
            if (showOrCloseText.isChecked()) {
                showOrCloseText.setText("Close Text");
                textView.setVisibility(View.VISIBLE);
            } else {
                showOrCloseText.setText("Show Text");
                textView.setVisibility(View.GONE);
            }
        });
    }

}