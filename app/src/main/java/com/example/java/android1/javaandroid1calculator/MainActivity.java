package com.example.java.android1.javaandroid1calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StateSave stateSave;
    private final String DISPLAY_KEY = "DISPLAY";
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonClickListener listener = new ButtonClickListener(this);
        display = findViewById(R.id.display);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        stateSave = new StateSave(this);
        outState.putParcelable(DISPLAY_KEY, stateSave);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        stateSave = savedInstanceState.getParcelable(DISPLAY_KEY);
        display.setText(stateSave.getDisplayValue());
    }
}