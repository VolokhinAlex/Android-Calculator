package com.example.java.android1.javaandroid1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {
    private MaterialButton mButtonBackToCalculate;
    private MaterialButton mButtonTurnOnLightTheme;
    private MaterialButton mButtonTurnOnDarkTheme;
    private boolean mIsLightTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_settings);
        mButtonBackToCalculate = findViewById(R.id.button_back_to_calculate);
        mButtonTurnOnLightTheme = findViewById(R.id.light_theme);
        mButtonTurnOnDarkTheme = findViewById(R.id.dark_theme);
        openActivity(mButtonBackToCalculate, Settings.this, MainActivity.class);
        clickListener(mButtonTurnOnLightTheme, true);
        clickListener(mButtonTurnOnDarkTheme, false);
    }

    private void clickListener(MaterialButton button, boolean theme) {
        button.setOnClickListener((view) -> {
            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.prefs, MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(MainActivity.prefs_theme_name, theme).apply();
            recreate();
        });
    }

    private void openActivity(MaterialButton button, Context currentContext, Class<?> classToSwitch) {
        button.setOnClickListener((view) -> {
            Intent intent = new Intent(currentContext, classToSwitch);
            intent.putExtra(MainActivity.prefs_theme_name, mIsLightTheme);
            startActivity(intent);
        });
    }

    protected void setTheme() {
        mIsLightTheme = getSharedPreferences(MainActivity.prefs, MODE_PRIVATE).
                getBoolean(MainActivity.prefs_theme_name, true);
        if (mIsLightTheme) {
            setTheme(R.style.ThemeLight);
        } else {
            setTheme(R.style.ThemeDark);
        }
    }

}