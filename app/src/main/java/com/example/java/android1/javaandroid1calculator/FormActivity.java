package com.example.java.android1.javaandroid1calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        selectDateOnCalendar();
        switchActivity();
    }

    private void selectDateOnCalendar() {
        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setVisibility(View.INVISIBLE);
        EditText dateTime = findViewById(R.id.date_field);
        dateTime.setOnClickListener(view -> {
            calendarView.setVisibility(View.VISIBLE);
            calendarView.setOnDateChangeListener((calendarView1, year, month, dayOfMonth) -> {
                dateTime.setText(String.format("%s/%s/%s", month, dayOfMonth, year));
                calendarView.setVisibility(View.INVISIBLE);
            });
        });
        switchActivity();
    }

    private void switchActivity() {
        Button mainActivity = findViewById(R.id.main_window);
        mainActivity.setOnClickListener(click -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

}