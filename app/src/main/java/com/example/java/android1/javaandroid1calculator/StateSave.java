package com.example.java.android1.javaandroid1calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class StateSave implements Parcelable {

    private TextView textView;
    private String display;

    public StateSave(MainActivity mainActivity) {
        textView = mainActivity.findViewById(R.id.display);
        display = textView.getText().toString();
    }

    protected StateSave(Parcel in) {
        display = in.readString();
    }

    public static final Creator<StateSave> CREATOR = new Creator<StateSave>() {
        @Override
        public StateSave createFromParcel(Parcel in) {
            return new StateSave(in);
        }

        @Override
        public StateSave[] newArray(int size) {
            return new StateSave[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(display);
    }

    public String getDisplayValue() {
        return display;
    }
}
