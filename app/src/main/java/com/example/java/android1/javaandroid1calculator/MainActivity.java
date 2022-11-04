package com.example.java.android1.javaandroid1calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private StateSave mStateSave;
    private final String DISPLAY_KEY = "DISPLAY";
    private TextView mDisplay;
    private ButtonClickListener mListener;
    private MaterialButton mButtonSettingActivity;
    public static final String prefs = "prefs.xml";
    public static final String prefs_theme_name = "theme";
    public static final String prefs_display_key = "display.key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_main);
        mButtonSettingActivity = findViewById(R.id.button_setting_activity);
        mListener = new ButtonClickListener(this);
        mDisplay = findViewById(R.id.display);
        openActivity(mButtonSettingActivity, MainActivity.this, Settings.class);
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

    private void openActivity(MaterialButton button, Context currentContext, Class<?> classToSwitch) {
        button.setOnClickListener((view) -> {
            Intent intent = new Intent(currentContext, classToSwitch);
            startActivity(intent);
        });
    }

    protected void setTheme() {
        boolean isLightTheme = getSharedPreferences(MainActivity.prefs, MODE_PRIVATE).
                getBoolean(prefs_theme_name, true);
        if (isLightTheme) {
            setTheme(R.style.ThemeLight);
        } else {
            setTheme(R.style.ThemeDark);
        }
    }

}