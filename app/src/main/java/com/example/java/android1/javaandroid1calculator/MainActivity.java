package com.example.java.android1.javaandroid1calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private StateSave mStateSave;
    private final String DISPLAY_KEY = "DISPLAY";
    private TextView mDisplay;
    private ButtonClickListener mListener;
    private MaterialButton mButtonLightTheme;
    private MaterialButton mButtonDarkTheme;
    private static final String prefs = "prefs.xml";
    private static final String prefs_theme_name = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isLightTheme = getSharedPreferences(prefs, MODE_PRIVATE).
                getBoolean(prefs_theme_name, true);
        if (isLightTheme) {
            setTheme(R.style.ThemeLight);
        } else {
            setTheme(R.style.ThemeDark);
        }
        setContentView(R.layout.activity_main);
        mButtonLightTheme = findViewById(R.id.light_theme);
        mButtonDarkTheme = findViewById(R.id.dark_theme);
        mListener = new ButtonClickListener(this);
        mDisplay = findViewById(R.id.display);
        clickListener(mButtonLightTheme, true);
        clickListener(mButtonDarkTheme, false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mStateSave = new StateSave(this);
        outState.putParcelable(DISPLAY_KEY, mStateSave);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mStateSave = savedInstanceState.getParcelable(DISPLAY_KEY);
        mDisplay.setText(mStateSave.getDisplayValue());
        mListener.setOldNumber(mStateSave.getDisplayValue());
        mListener.setOperator(mStateSave.getOperator());
        mListener.setIsFirstValue(mStateSave.getFlagIfValueFirst());
    }

    private void clickListener(MaterialButton button, boolean theme) {
        button.setOnClickListener((view) -> {
            SharedPreferences sharedPreferences = getSharedPreferences(prefs, MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(prefs_theme_name, theme).apply();
            recreate();
        });
    }


}