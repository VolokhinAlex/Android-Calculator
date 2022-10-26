package com.example.java.android1.javaandroid1calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class StateSave implements Parcelable {

    private TextView mTextView;
    private String mDisplay;
    private String mOperator;

    public StateSave(MainActivity mainActivity) {
        mTextView = mainActivity.findViewById(R.id.display);
        mDisplay = mTextView.getText().toString();
        String[] operations = new String[]{"+", "-", "/", "*", "%"};
        for (String operation : operations) {
            if (mDisplay.contains(operation)) {
                mOperator = operation;
            }
        }
    }

    protected StateSave(Parcel in) {
        mDisplay = in.readString();
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
        parcel.writeString(mDisplay);
        parcel.writeString(mOperator);
    }

    public String getDisplayValue() {
        return mDisplay;
    }

    public String getOperator() {
        return mOperator;
    }

    public boolean getFlagIfValueFirst() {
        return mDisplay.equals("");
    }
}
