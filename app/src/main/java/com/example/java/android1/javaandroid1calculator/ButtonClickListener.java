package com.example.java.android1.javaandroid1calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonClickListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private final String INFO_TAG = "INFO_TAG";
    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;
    private Button mButtonFour;
    private Button mButtonFive;
    private Button mButtonSix;
    private Button mButtonSeven;
    private Button mButtonEight;
    private Button mButtonNine;
    private Button mButtonZero;
    private Button mButtonPoint;

    private Button mButtonMultiplication;
    private Button mButtonDivision;
    private Button mButtonMinus;
    private Button mButtonPlus;
    private Button mButtonEquals;
    private Button mButtonPercent;
    private Button mButtonDelete;
    private Button mButtonClear;
    private TextView mDisplayLabel;
    private String mOperator;
    private String mOldNumber;
    private Double result;

    public ButtonClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        initialization();
        mButtonOne.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonThree.setOnClickListener(this);
        mButtonFour.setOnClickListener(this);
        mButtonFive.setOnClickListener(this);
        mButtonSix.setOnClickListener(this);
        mButtonSeven.setOnClickListener(this);
        mButtonEight.setOnClickListener(this);
        mButtonNine.setOnClickListener(this);
        mButtonZero.setOnClickListener(this);
        mButtonPoint.setOnClickListener(this);
        mButtonMultiplication.setOnClickListener(this);
        mButtonDivision.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);
        mButtonPlus.setOnClickListener(this);
        mButtonEquals.setOnClickListener(this);
        mButtonPercent.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
        mButtonClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int widgetId = view.getId();
        switch (widgetId) {
            case R.id.button_one:
                setValueOnDisplay((String) mButtonOne.getText());
                break;
            case R.id.button_two:
                setValueOnDisplay((String) mButtonTwo.getText());
                break;
            case R.id.button_three:
                setValueOnDisplay((String) mButtonThree.getText());
                break;
            case R.id.button_four:
                setValueOnDisplay((String) mButtonFour.getText());
                break;
            case R.id.button_five:
                setValueOnDisplay((String) mButtonFive.getText());
                break;
            case R.id.button_six:
                setValueOnDisplay((String) mButtonSix.getText());
                break;
            case R.id.button_seven:
                setValueOnDisplay((String) mButtonSeven.getText());
                break;
            case R.id.button_eight:
                setValueOnDisplay((String) mButtonEight.getText());
                break;
            case R.id.button_nine:
                setValueOnDisplay((String) mButtonNine.getText());
                break;
            case R.id.button_zero:
                setValueOnDisplay((String) mButtonZero.getText());
                break;
            case R.id.button_point:
                setValueOnDisplay((String) mButtonPoint.getText());
                break;
            case R.id.button_multiplication:
                setValueOnDisplay((String) mButtonMultiplication.getText());
                mOperator = "*";
                break;
            case R.id.button_division:
                setValueOnDisplay((String) mButtonDivision.getText());
                mOperator = "/";
                break;
            case R.id.button_plus:
                setValueOnDisplay((String) mButtonPlus.getText());
                mOperator = "+";
                break;
            case R.id.button_minus:
                setValueOnDisplay((String) mButtonMinus.getText());
                mOperator = "-";
                break;
            case R.id.button_result:
                equalsValue();
                mOperator = "=";
                break;
            case R.id.button_percent:
                setValueOnDisplay((String) mButtonPercent.getText());
                mOperator = "%";
                break;
            case R.id.button_clear:
                mDisplayLabel.setText("");
                break;
            case R.id.button_delete:
                String text = mDisplayLabel.getText().toString();
                if (text.length() <= 0) return;
                mDisplayLabel.setText(text.substring(0, text.length() - 1));
                break;
            default:
                throw new RuntimeException("Undefined widget id: " + widgetId);
        }
    }

    public void initialization() {
        mButtonOne = mainActivity.findViewById(R.id.button_one);
        mButtonTwo = mainActivity.findViewById(R.id.button_two);
        mButtonThree = mainActivity.findViewById(R.id.button_three);
        mButtonFour = mainActivity.findViewById(R.id.button_four);
        mButtonFive = mainActivity.findViewById(R.id.button_five);
        mButtonSix = mainActivity.findViewById(R.id.button_six);
        mButtonSeven = mainActivity.findViewById(R.id.button_seven);
        mButtonEight = mainActivity.findViewById(R.id.button_eight);
        mButtonNine = mainActivity.findViewById(R.id.button_nine);
        mButtonZero = mainActivity.findViewById(R.id.button_zero);
        mButtonPoint = mainActivity.findViewById(R.id.button_point);

        mButtonMultiplication = mainActivity.findViewById(R.id.button_multiplication);
        mButtonDivision = mainActivity.findViewById(R.id.button_division);
        mButtonMinus = mainActivity.findViewById(R.id.button_minus);
        mButtonPlus = mainActivity.findViewById(R.id.button_plus);
        mButtonEquals = mainActivity.findViewById(R.id.button_result);
        mButtonPercent = mainActivity.findViewById(R.id.button_percent);
        mButtonDelete = mainActivity.findViewById(R.id.button_delete);
        mButtonClear = mainActivity.findViewById(R.id.button_clear);
        mDisplayLabel = mainActivity.findViewById(R.id.display);
    }

    private void setValueOnDisplay(String text) {
        if (mDisplayLabel.getText().toString().equals("0") && mDisplayLabel.getText().length() <= 1 && mOperator == null) {
            mDisplayLabel.setText("");
        }
        if (result != null) {
            mDisplayLabel.setText("");
            result = null;
        }
        mDisplayLabel.append(text);
        mOldNumber = mDisplayLabel.getText().toString();
    }

    private void equalsValue() {
        String number = mDisplayLabel.getText().toString();
        if (number.length() > 1) {
            try {
                number = number.substring(number.lastIndexOf(mOperator) + 1);
                if (number.length() > 1) {
                    operations(Double.parseDouble(number));
                } else {
                    operations(1);
                }
            } catch (NumberFormatException e) {
                mDisplayLabel.setText("");
            }
        }
    }

    private void operations(double number) {
        double oldNumber = Double.parseDouble(mOldNumber.substring(0, mOldNumber.lastIndexOf(mOperator)));
        switch (mOperator) {
            case "*":
                result = oldNumber * number;
                mDisplayLabel.setText(String.valueOf(result));
                break;
            case "/":
                result = oldNumber / number;
                mDisplayLabel.setText(String.valueOf(result));
                break;
            case "+":
                result = oldNumber + number;
                mDisplayLabel.setText(String.valueOf(result));
                break;
            case "-":
                result = oldNumber - number;
                mDisplayLabel.setText(String.valueOf(result));
                break;
            case "%":
                result = oldNumber % number;
                mDisplayLabel.setText(String.valueOf(result));
                break;
        }
    }
}
