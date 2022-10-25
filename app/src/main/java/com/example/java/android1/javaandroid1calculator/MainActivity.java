package com.example.java.android1.javaandroid1calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StateSave stateSave;
    private final String DISPLAY_KEY = "DISPLAY";
    private TextView mDisplay;
    private ButtonClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = new ButtonClickListener(this);
        mDisplay = findViewById(R.id.display);
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
        mDisplay.setText(stateSave.getDisplayValue());
        listener.setOldNumber(stateSave.getDisplayValue());
        listener.setOperator(stateSave.getOperator());
        listener.setFlagIfValueFirst(stateSave.getFlagIfValueFirst());
    }
}